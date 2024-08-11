package com.application.memdb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "Order_Table")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq_gen")
    @SequenceGenerator(name = "order_seq_gen", sequenceName = "order_seq", allocationSize = 1)
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "mobile_number")
    private String mobileNumber;
//    Use LocalDate if you only need to represent dates without time.
//    Use LocalDateTime if you need to represent both date and time without considering time zones.
//    Use ZonedDateTime if you need to handle date and time with time zone information.
//    JSON Format vs. LocalDateTime Format:
//    The JSON payload uses a timestamp format "2024-08-10 01:00:00.34" (which resembles yyyy-MM-dd HH:mm:ss.SSS).
//    LocalDateTime expects the timestamp in the ISO-8601 format (yyyy-MM-dd'T'HH:mm:ss.SSS) by default.
    @Column(name = "order_time")
    private LocalDateTime orderTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    // To ignore infinite recursion for toString() method
    private List<Item> items;

    public Order() {
    }

    public Order(Long orderId, String customerName, List<Item> items) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
        for (Item item : items) {
            item.setOrder(this);
        }
    }

    public void addItem(Item item) {
        items.add(item);
        item.setOrder(this);
    }

    public void removeItem(Item item) {
        items.remove(item);
        item.setOrder(null);
    }

    public Long getOrderId() {
        return orderId;
    }
}
