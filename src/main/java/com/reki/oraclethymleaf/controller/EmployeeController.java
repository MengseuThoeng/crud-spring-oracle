//package com.reki.oraclethymleaf.controller;
//
//
//import com.reki.oraclethymleaf.domain.Employee;
//import com.reki.oraclethymleaf.service.EmployeeService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/employee")
//@RequiredArgsConstructor
//public class EmployeeController {
//
//    private final EmployeeService employeeService;
//
//
//    @GetMapping
//    public Iterable<Employee> getAllEmployee() {
//        return employeeService.getAllEmployee();
//    }
//
//    @PostMapping("/save")
//    Employee saveEmployee(@RequestBody Employee employee) {
//        return employeeService.addEmployee(employee);
//    }
//
//    @GetMapping("/{id}")
//    Employee getEmployeeById(@PathVariable Long id) {
//        return employeeService.getEmployeeById(id);
//    }
//
//    @PatchMapping("/update/{id}")
//    Employee updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
//        return employeeService.updateEmployee(employee,id);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    Map<String, String> deleteEmployee(@PathVariable Long id) {
//        return employeeService.deleteEmployee(id);
//    }
//
//
//}
