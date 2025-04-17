package com.reki.oraclethymleaf.base;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BasedError<T> {
    private String code;
    private T description;
}
