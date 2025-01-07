package com.cmdotenter.VetCare.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class BaseOrderRequest {
    private Long userId;
    private LocalDateTime date;
    private Double totalPrice;
}
