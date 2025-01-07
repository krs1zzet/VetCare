package com.cmdotenter.VetCare.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseVaccineRequest {
    private String name;
    private String description;
    private Integer count;
    private Integer periodDay;
}
