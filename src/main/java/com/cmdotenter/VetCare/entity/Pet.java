package com.cmdotenter.VetCare.entity;

import jakarta.persistence.*;
import org.hibernate.type.descriptor.jdbc.SmallIntJdbcType;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@Table(name = "Pets")
public class Pet {
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pet_id_seq")
    @SequenceGenerator(name = "pet_id_seq",sequenceName = "pet_id_seq", allocationSize = 1)
    @Id
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String species;

    private Integer age;

    @Column(nullable = false)
    private Float weight;

    private Float height;

    private Boolean isSterile;

    @Column(length = 50)
    private String allergies;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;


    @OneToMany(mappedBy = "pet")
    List<PetVaccine> petVaccines;

    @OneToMany(mappedBy = "pet")
    List<Appointment> Appointments;





}
