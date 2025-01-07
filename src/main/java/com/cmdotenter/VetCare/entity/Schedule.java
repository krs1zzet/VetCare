package com.cmdotenter.VetCare.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Schedules")
public class Schedule {
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE,generator = "schedule_id_seq")
    @SequenceGenerator(name = "schedule_id_seq",sequenceName = "schedule_id_seq", allocationSize = 1)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "clinic_id",nullable = false)
    private Clinic clinic;

    @Column(name = "is_available")
    private Boolean isAvailable;

    @Column(name= "date")
    private LocalDateTime date;
}
