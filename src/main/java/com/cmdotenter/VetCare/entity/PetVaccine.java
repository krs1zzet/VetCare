package com.cmdotenter.VetCare.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@Table(name = "PetVaccines")
public class PetVaccine {
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pet_vaccine_id_seq")
    @SequenceGenerator(name = "pet_vaccine_id_seq",sequenceName = "pet_vaccine_id_seq", allocationSize = 1)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pet_id",nullable = false)
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "vaccine_id",nullable = false)
    private Vaccine vaccine;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "next_date")
    private LocalDateTime nextDate;

    @Column(name = "count")
    private Integer count;



}
