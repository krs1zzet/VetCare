package com.cmdotenter.VetCare.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@Table(name = "OrderDetails")
public class OrderDetails {
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "order_details_id_seq")
    @SequenceGenerator(name = "order_details_id_seq",sequenceName = "order_details_id_seq", allocationSize = 1)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;

}
