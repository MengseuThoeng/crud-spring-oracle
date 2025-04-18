package com.reki.oraclethymleaf.dto;

import com.reki.oraclethymleaf.domain.User;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private User user;
}

