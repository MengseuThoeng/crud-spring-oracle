package com.reki.oraclethymleaf.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Employee {
    @Id
    private Long id;

    private String name;

    private String job;

    private Long salary;

    private Long commission;

}
