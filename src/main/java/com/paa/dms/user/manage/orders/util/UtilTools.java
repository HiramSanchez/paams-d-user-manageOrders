package com.paa.dms.user.manage.orders.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.security.SecureRandom;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
@Component
/**
 * Utility class providing helper methods and mocks.
 */
public class UtilTools {

    /**
     * Returns the current date formatted as "MM-dd-yyyy".
     * @return A string representation of the current date in the specified format.
     */
    public String getDateFormatted(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return currentDate.format(formatter);
    }

    /**
     * Generates a unique order ID based on the current date and a random six-digit number.
     * The order ID format is "MMddyyyy" followed by a six-digit random number.
     * @return A string representing the unique order ID.
     */
    public String getOrderId(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String dateCode = currentDate.format(formatter);
        SecureRandom secureRandom = new SecureRandom();
        int randomSix = secureRandom.nextInt(900000) + 100000;
        return dateCode + randomSix;
    }

    /**
     * Generates a guide number from a random number to mock an external provider's API
     * The guide number is formed by a fifteen-digit random number.
     * @return A string representing the guide number.
     */
    public String getGuideNumber(String orderId){
        String orderCode = orderId.substring(8);
        SecureRandom secureRandom = new SecureRandom();
        int randomNine = secureRandom.nextInt(900000000) + 100000000;
        return orderCode + randomNine;
    }
}
