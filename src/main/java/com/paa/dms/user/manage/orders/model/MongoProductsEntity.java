package com.paa.dms.user.manage.orders.model;

import com.paa.dms.user.manage.orders.constants.MongoConstants;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = MongoConstants.MARKETPLACE_PRODUCTS_COLLECTION)
public class MongoProductsEntity {
    private ObjectId _id;
    private String color;
    private String description;
    private String imageId;
    private String name;
    private String productId;
    private String productType;
    private String size;
    private String price;

}
