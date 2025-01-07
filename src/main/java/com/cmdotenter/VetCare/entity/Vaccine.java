package com.cmdotenter.VetCare.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Vaccines")
public class Vaccine {
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "vaccine_id_seq")
    @SequenceGenerator(name = "vaccine_id_seq",sequenceName = "vaccine_id_seq", allocationSize = 1)
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "count")
    private Integer count;

    @Column(name = "period_day")
    private Integer periodDay;

    @OneToMany(mappedBy = "vaccine")
    private List<PetVaccine> petVaccines;


}
