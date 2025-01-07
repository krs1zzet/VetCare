package com.cmdotenter.VetCare.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@Table(name = "Products")
public class Product {
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE,generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq",sequenceName = "product_id_seq", allocationSize = 1)
    @Id
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Float price;

    @Column(name = "stock")
    private Integer stock;



}
