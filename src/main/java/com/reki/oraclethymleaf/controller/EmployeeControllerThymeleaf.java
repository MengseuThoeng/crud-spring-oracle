package com.reki.oraclethymleaf.controller;

import com.reki.oraclethymleaf.domain.Employee;
import com.reki.oraclethymleaf.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeControllerThymeleaf {

    private final EmployeeService employeeService;

    @GetMapping
    String getEmployee(Model model) {

        Iterable<Employee> employees = employeeService.getAllEmployee();

        model.addAttribute("employees", employees);
        return "Employees";
    }

    ;


    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable Long id, @Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "Employees";
        }
        employeeService.updateEmployee(employee, id);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }

    @PostMapping("/add")
    String addEmployee(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "Employees";
        }
        log.info("add employee " + employee);
        employeeService.addEmployee(employee);
        return "redirect:/employees";
    }


}
