package com.reki.oraclethymleaf.init;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit {



    @PostConstruct
    public void init() {


        System.out.println("Data initialization complete.");
    }

}
