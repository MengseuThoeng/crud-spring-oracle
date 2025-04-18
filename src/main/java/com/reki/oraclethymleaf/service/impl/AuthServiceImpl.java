package com.reki.oraclethymleaf.service.impl;

import com.reki.oraclethymleaf.domain.Employee;
import com.reki.oraclethymleaf.domain.User;
import com.reki.oraclethymleaf.dto.LoginResponse;
import com.reki.oraclethymleaf.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JdbcTemplate jdbcTemplate;


    @Override
    public User login(String username, String password) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("LOGIN")
                .returningResultSet("DATAOUTPUT", (rs, rowNum) -> {
                    try {
                        // Try mapping as a User
                        Long id = rs.getLong("ID");
                        String uname = rs.getString("USER_NAME");
                        String pwd = rs.getString("PASSWORD");
                        return new User(
                                id,
                                uname,
                                pwd,
                                rs.getString("PROFILE"),
                                rs.getString("PHONE_NUMBER"),
                                rs.getString("IS_ACTIVE"),
                                rs.getString("FIRST_NAME"),
                                rs.getString("LAST_NAME")
                        );
                    } catch (Exception e) {
                        return new User(null, null, null, null, null, null, null, null);
                    }
                });

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("P_USERNAME", username)
                .addValue("P_PASSWORD", password);

        Map<String, Object> out = jdbcCall.execute(in);

        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) out.get("DATAOUTPUT");

        if (users == null || users.isEmpty() || users.get(0).getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Invalid username or password"
            );
        }

        return users.get(0);
    }
}
