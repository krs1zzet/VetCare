package com.cmdotenter.VetCare.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@Table(name = "Clinics")
public class Clinic {
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE,generator = "clinic_id_seq")
    @SequenceGenerator(name = "clinic_id_seq",sequenceName = "clinic_id_seq", allocationSize = 1)
    @Id
    private Long id;

    @Column(name = "name",unique = true,nullable = false)
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "phone")
    private String phone;

}
