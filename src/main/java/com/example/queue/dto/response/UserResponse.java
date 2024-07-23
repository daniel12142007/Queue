package com.example.queue.dto.response;

import com.example.queue.model.enums.Role;

public record UserResponse(Long id,
                           String email,
                           String jwt,
                           Role role) {
}