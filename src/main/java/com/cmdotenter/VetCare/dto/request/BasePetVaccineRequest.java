package com.cmdotenter.VetCare.dto.request;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class BasePetVaccineRequest {

    private Long petId;
    private Long vaccineId;
    private LocalDateTime date;
    private LocalDateTime nextDate;
    private Integer count;
}
