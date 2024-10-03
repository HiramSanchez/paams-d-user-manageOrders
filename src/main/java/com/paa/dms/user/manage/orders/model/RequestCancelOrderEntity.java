package com.paa.dms.user.manage.orders.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RequestCancelOrderEntity {
    /** Unique identifier for the order. */
    @NotBlank(message = "is mandatory")
    @Positive(message = "must be a positive number")
    private String orderID;
}
