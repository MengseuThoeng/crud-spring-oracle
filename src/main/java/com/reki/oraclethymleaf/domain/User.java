package com.reki.oraclethymleaf.domain;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    private Long id;

    private String username;

    private String password;

    private String profile;

    private String phoneNumber;

    private String isActive;

    private String firstName;

    private String lastName;
}
