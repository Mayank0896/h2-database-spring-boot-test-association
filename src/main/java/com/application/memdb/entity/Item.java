package com.application.memdb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Item_Table")
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq_gen")
    @SequenceGenerator(name = "item_seq_gen", sequenceName = "item_seq", allocationSize = 1)
    @Column(name = "item_id")
    private Long itemId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price")
    private Double price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "order_id")
    // If you're using Jackson for JSON serialization, you can use the @JsonIgnore annotation to prevent recursive serialization, it does not make order null, only while searlizing to JSON it stops recursion
    @JsonIgnore
    private Order order;

    public Item() {
    }

    public Item(Long itemId, String productName, int quantity, Order order) {
        this.itemId = itemId;
        this.productName = productName;
        this.quantity = quantity;
        this.order = order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
