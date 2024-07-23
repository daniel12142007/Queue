package com.example.queue.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    OWNER,
    USER,
    BLOCK;

    @Override
    public String getAuthority() {
        return name();
    }
}