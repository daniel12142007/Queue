package com.example.queue.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @Email(message = "Enter the correct email format")
    private String email;
    private String password;
}