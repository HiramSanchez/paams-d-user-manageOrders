package com.paa.dms.user.manage.orders.exception.custom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoDataFoundExceptionTest {
    @Test
    void testNoDataFoundException() {
        // Act
        Exception exception = assertThrows(NoDataFoundException.class, () -> {throw new NoDataFoundException();});

        // Assert
        assertNotNull(exception);
        assertEquals(NoDataFoundException.class, exception.getClass());
    }

}