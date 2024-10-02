package com.paa.dms.user.manage.orders.model;

import lombok.Data;

@Data
public class ProductEntity {
    private String productId;
    private String name;
    private String imageId;
    private String quantity;
    private String price;

}
