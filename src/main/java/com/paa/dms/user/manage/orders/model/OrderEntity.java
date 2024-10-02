package com.paa.dms.user.manage.orders.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderEntity {
    private String orderDate;
    private String orderID;
    private String uid;
    private String orderTotal;
    private String guideNumber;
    private String orderStatus;
    private List<ProductEntity> orderList;

}
