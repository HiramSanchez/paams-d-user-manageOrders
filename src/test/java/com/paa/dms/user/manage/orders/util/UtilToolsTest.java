package com.paa.dms.user.manage.orders.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UtilToolsTest {
    @InjectMocks
    private UtilTools utilTools;
    @Mock
    private SecureRandom secureRandom;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetDateFormatted() {
        // Arrange
        LocalDate fixedDate = LocalDate.of(2024, 10, 18);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String expectedDate = fixedDate.format(formatter);
        // Arrange
        String actualDate = utilTools.getDateFormatted();
        assertEquals(expectedDate, actualDate, "The date format should match the expected value.");
    }
    @Test
    void testGetOrderId() {
        // Arrange
        LocalDate fixedDate = LocalDate.of(2024, 10, 18);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String expectedDatePart = fixedDate.format(formatter);
        // Act
        when(secureRandom.nextInt(900000)+100000).thenReturn(123456);
        String expectedOrderId = expectedDatePart + "223456";
        // Arrange
        String actualOrderId = utilTools.getOrderId();
        assertEquals(expectedOrderId, actualOrderId, "The Order ID should match the expected value.");
    }
    @Test
    void testGetGuideNumber() {
        // Arrange
        String orderId = "10182024123456";
        when(secureRandom.nextInt(900000000)).thenReturn(123456789);
        String expectedGuideNumber = "123456223456789";
        // Assert
        String actualGuideNumber = utilTools.getGuideNumber(orderId);
        assertEquals(expectedGuideNumber, actualGuideNumber, "The Guide Number should match the expected value.");
    }

}