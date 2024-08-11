package com.application.memdb.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "address")
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq_gen")
    @SequenceGenerator(name = "address_seq_gen", sequenceName = "address_seq", allocationSize = 1)
    @Column(name = "address_id")
    private Long id;
    @Column(name = "address_line")
    private String addressLine;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "country")
    private String country;
    @Column(name = "pincode")
    private Integer pincode;

    public Address() {
    }

    public Address(Long id, String addressLine, String city, String state, String country, Integer pincode) {
        this.id = id;
        this.addressLine = addressLine;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }
}
