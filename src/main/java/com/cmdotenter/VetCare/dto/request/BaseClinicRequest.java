package com.cmdotenter.VetCare.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseClinicRequest {
    private String name;
    private String location;
    private String number;

}
