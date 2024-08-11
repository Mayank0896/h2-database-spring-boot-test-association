package com.application.memdb.entity;

import com.application.memdb.constant.Department;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq_gen")
    @SequenceGenerator(name = "employee_seq_gen", sequenceName = "employee_seq", allocationSize = 1)
    @Column(name = "employee_id")
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String empFirstName;
    @Column(name = "last_name")
    private String empLastName;
    @Column(name = "email")
    private String email;
    @Column(name = "department")
    @Enumerated(EnumType.STRING)
    private Department department;
    @Column(name = "salary")
    private double salary;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    public Employee() {
    }

    public Employee(Long id, String empFirstName, String empLastName, String email, Department department, double salary) {
        this.id = id;
        this.empFirstName = empFirstName;
        this.empLastName = empLastName;
        this.email = email;
        this.department = department;
        this.salary = salary;
    }
}
