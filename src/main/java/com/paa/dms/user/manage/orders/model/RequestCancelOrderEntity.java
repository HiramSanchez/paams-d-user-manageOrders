package com.paa.dms.user.manage.orders.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RequestCancelOrderEntity {
    @NotBlank(message = "is mandatory")
    @Positive(message = "must be a positive number")
    @Pattern(regexp = "^\\d{14}$", message="must be a 16 digit number")
    private String orderID;
}
