package com.reki.oraclethymleaf.controller;


import com.reki.oraclethymleaf.domain.User;
import com.reki.oraclethymleaf.dto.LoginResponse;
import com.reki.oraclethymleaf.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    User login(@RequestParam String username,
               @RequestParam String password) {
        return authService.login(username, password);
    }

}
