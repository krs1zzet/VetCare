package com.cmdotenter.VetCare.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseProductRequest {
    private String name;
    private String description;
    private Float price;
    private Integer stock;
}
