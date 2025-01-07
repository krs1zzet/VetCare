package com.cmdotenter.VetCare.dto.request;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BasePetRequest {
    private String name;
    private String species;
    private Integer age;
    private Float weight;
    private Float height;
    private Boolean isSterile;
    private String allergies;
    private Long userId;


}
