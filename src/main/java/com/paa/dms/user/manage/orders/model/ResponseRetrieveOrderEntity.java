package com.paa.dms.user.manage.orders.model;

import lombok.Data;

import java.util.List;

@Data
public class ResponseRetrieveOrderEntity {
    /** List of orders related to the uid provided. */
    private List<OrderEntity> orders;
}
