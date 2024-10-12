package com.paa.dms.user.manage.orders.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RequestChangeOrderEntity {
    /** Unique identifier for the order. */
    @NotBlank(message = " is mandatory")
    @Positive(message = " must be a positive number")
    private String orderID;
    /** State of the order (shipped, canceled, etc.). */
    @NotBlank(message = " is mandatory")
    private String state;
}
