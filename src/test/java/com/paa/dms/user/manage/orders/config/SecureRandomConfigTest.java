package com.paa.dms.user.manage.orders.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.security.SecureRandom;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = SecureRandomConfig.class)
class SecureRandomConfigTest {
    @Autowired
    private SecureRandom secureRandom;  // Inject the bean from context
    @Test
    void testSecureRandomBeanCreation() {
        assertNotNull(secureRandom, "The SecureRandom bean should not be null.");
    }
}