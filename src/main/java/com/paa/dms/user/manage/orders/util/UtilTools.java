package com.paa.dms.user.manage.orders.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
@Component
public class UtilTools {

    public String getDateFormatted(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return currentDate.format(formatter);
    }

    public String getOrderId(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String dateCode = currentDate.format(formatter);
        int randomSix = ThreadLocalRandom.current().nextInt(100000, 1000000);
        String orderId = dateCode + randomSix;
        return orderId;
    }
}
