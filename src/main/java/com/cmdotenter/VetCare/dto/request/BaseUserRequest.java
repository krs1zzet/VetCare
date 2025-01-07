package com.cmdotenter.VetCare.dto.request;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseUserRequest {
    private String name;
    private String email;
    private String password;
    private Short age;
}
