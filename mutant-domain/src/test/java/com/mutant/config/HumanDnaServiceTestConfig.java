package com.mutant.config;

import com.mutant.service.HumanDnaService;
import com.mutant.service.impl.HumanDnaServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author Juan Fajardo
 * @version 1.0-SNAPSHOT
 */
@TestConfiguration
public class HumanDnaServiceTestConfig {

    @Bean
    public HumanDnaService portfolioService() {
        return new HumanDnaServiceImpl();
    }
}
