package com.example.queue.model;

import com.example.queue.model.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String password;
    private String tokenGoogle;
    private LocalDateTime dateRegister;
    private LocalDateTime dateFinishOwner;
    @Enumerated(EnumType.STRING)
    private Role role;
}