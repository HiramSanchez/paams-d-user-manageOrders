package com.paa.dms.user.manage.orders.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.util.List;

@Data
public class RequestNewOrderEntity {
    /** List of products included in the order. */
    @NotEmpty(message = "is mandatory")
    private List<ProductEntity> orderList;
    /** Total cost of the order. */
    @NotBlank(message = "is mandatory")
    @Positive(message = "must be a positive number")
    private String orderTotal;
}
