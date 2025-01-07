package com.cmdotenter.VetCare.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BaseAppointmentRequest {
    private LocalDateTime date;
    private Long userId;
    private Long petId;
    private Long clinicId;
}
