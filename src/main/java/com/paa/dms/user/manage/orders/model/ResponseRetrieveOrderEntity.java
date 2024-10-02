package com.paa.dms.user.manage.orders.model;

import lombok.Data;

import java.util.List;

@Data
public class ResponseRetrieveOrderEntity {
    private List<OrderEntity> orders;
}
