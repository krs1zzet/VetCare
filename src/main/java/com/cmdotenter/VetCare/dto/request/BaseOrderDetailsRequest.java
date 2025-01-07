package com.cmdotenter.VetCare.dto.request;

import com.cmdotenter.VetCare.entity.Order;
import com.cmdotenter.VetCare.entity.Product;
import com.cmdotenter.VetCare.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BaseOrderDetailsRequest {
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private Double price;
}
