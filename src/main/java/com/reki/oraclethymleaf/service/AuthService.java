package com.reki.oraclethymleaf.service;

import com.reki.oraclethymleaf.domain.User;
import com.reki.oraclethymleaf.dto.LoginResponse;

public interface AuthService {

    User login(String username, String password);
}
