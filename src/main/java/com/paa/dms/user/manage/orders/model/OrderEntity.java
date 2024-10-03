package com.paa.dms.user.manage.orders.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderEntity {
    /** Date when the order was placed. */
    private String orderDate;
    /** Unique identifier for the order. */
    private String orderID;
    /** Unique identifier for the user who placed the order. */
    private String uid;
    /** Total cost of the order. */
    private String orderTotal;
    /** Tracking number or guide number associated with the delivery of the order.*/
    private String guideNumber;
    /** Current status of the order (e.g., "Placed", "Shipped", "Delivered", "Canceled").*/
    private String orderStatus;
    /** List of products included in the order.*/
    private List<ProductEntity> orderList;

}
