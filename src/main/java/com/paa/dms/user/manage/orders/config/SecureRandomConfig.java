package com.paa.dms.user.manage.orders.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.security.SecureRandom;

@Configuration
public class SecureRandomConfig {
    @Bean
    public SecureRandom secureRandom() {
        return new SecureRandom();
    }
}
