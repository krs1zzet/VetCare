package com.cmdotenter.VetCare.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@Table(name = "Appointment")
public class Appointment {

    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "appointment_id_seq")
    @SequenceGenerator(name = "appointment_id_seq", sequenceName = "appointment_id_seq", allocationSize = 1)
    @Id
    private Long id;

    @Column(name = "date",nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "pet_id",nullable = false)
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "clinic_id",nullable = false)
    private Clinic clinic;

}
