package com.reki.oraclethymleaf.service.impl;

import com.reki.oraclethymleaf.domain.Employee;
import com.reki.oraclethymleaf.repository.EmployeeRepository;
import com.reki.oraclethymleaf.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;


@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final  EmployeeRepository employeeRepository;

    private final JdbcTemplate jdbcTemplate;


    @Override
    public Iterable<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee addEmployee(Employee employee) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("ADD_EMP")
                .returningResultSet("DATAOUTPUT",
                        (rs, rowNum) -> new Employee(
                                rs.getLong("ID"),
                                rs.getString("NAME"),
                                rs.getString("JOB"),
                                rs.getLong("SALARY"),
                                rs.getLong("COMMISSION")
                        ));

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("P_ID", employee.getId())
                .addValue("P_NAME", employee.getName())
                .addValue("P_JOB", employee.getJob())
                .addValue("P_SALARY", employee.getSalary())
                .addValue("P_COMMISSION", employee.getCommission());

        Map<String, Object> out = jdbcCall.execute(in);

        @SuppressWarnings("unchecked")
        List<Employee> employees = (List<Employee>) out.get("DATAOUTPUT");



        return employees.getFirst();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("GET_EMP_BY_ID")
                .returningResultSet("DATAOUTPUT",
                        (rs, rowNum) -> new Employee(
                                rs.getLong("ID"),
                                rs.getString("NAME"),
                                rs.getString("JOB"),
                                rs.getLong("SALARY"),
                                rs.getLong("COMMISSION")
                        ));

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("P_ID", id);

        Map<String, Object> out = jdbcCall.execute(in);

        List<Employee> employeeList = (List<Employee>) out.get("DATAOUTPUT");

        if (employeeList == null || employeeList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employees.html not found");
        }

        return employeeList.get(0);
    }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {

        log.info(employee.toString());
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("UPDATE_EMP_BY_ID")
                .returningResultSet("DATAOUTPUT",
                        (rs, rowNum) -> new Employee(
                                rs.getLong("ID"),
                                rs.getString("NAME"),
                                rs.getString("JOB"),
                                rs.getLong("SALARY"),
                                rs.getLong("COMMISSION")
                        ));
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("P_ID", id)
                .addValue("P_NAME", employee.getName())
                .addValue("P_JOB", employee.getJob())
                .addValue("P_SALARY", employee.getSalary())
                .addValue("P_COMMISSION", employee.getCommission());
        Map<String, Object> out = jdbcCall.execute(in);
        List<Employee> employeeList = (List<Employee>) out.get("DATAOUTPUT");

        return  employeeList.isEmpty() ? null : employeeList.get(0);
    }

    @Override
    public Map<String, String> deleteEmployee(Long id) {
        Employee existing = getEmployeeById(id);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employees.html not found");
        }

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("DEL_EMP_BY_ID");

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("P_ID", id);

        jdbcCall.execute(in);

        return Map.of("success", "delete employee successfully");
    }
}
