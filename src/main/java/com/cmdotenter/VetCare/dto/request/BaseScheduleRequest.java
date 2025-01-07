package com.cmdotenter.VetCare.dto.request;

import com.cmdotenter.VetCare.entity.Clinic;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BaseScheduleRequest {
    private Long clinicId;
    private Boolean isAvailable;
    private LocalDateTime date;
}
