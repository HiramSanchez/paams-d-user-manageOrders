package com.paa.dms.user.manage.orders.model;

import lombok.Data;

@Data
public class ProductEntity {
    /** Unique identifier for the product. */
    private String productId;
    /** Name of the product. */
    private String name;
    /** Unique identifier for the image associated with the product. */
    private String imageId;
    /** Quantity of the product ordered. */
    private String quantity;
    /** Price of the product. */
    private String price;

}
