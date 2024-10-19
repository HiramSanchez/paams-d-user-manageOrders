package com.paa.dms.user.manage.orders;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class MainTest {
    @Test
    void contextLoads() {
        assertDoesNotThrow(() -> Main.main(new String[] {}));
    }

}