package com.reki.oraclethymleaf.service;

import com.reki.oraclethymleaf.domain.Employee;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    Iterable<Employee> getAllEmployee();

    Employee addEmployee(Employee employee);

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Employee employee,  Long id);

    Map<String, String> deleteEmployee(Long id);
}
