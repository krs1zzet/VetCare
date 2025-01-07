package com.cmdotenter.VetCare.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@Table(name = "Orders")
public class Order {

    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "order_id_seq")
    @SequenceGenerator(name = "order_id_seq", sequenceName = "order_id_seq", allocationSize = 1)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Column(nullable = false,name = "date")
    private LocalDateTime date;

    @Column(name = "total_price")
    private Double totalPrice;


}
