package com.paa.dms.user.manage.orders.model;

import lombok.Data;
import java.util.List;

@Data
public class RequestNewOrderEntity {
    private List<ProductEntity> orderList;
    private String orderTotal;
}
