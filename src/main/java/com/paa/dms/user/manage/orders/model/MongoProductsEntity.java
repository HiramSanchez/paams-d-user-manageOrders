package com.paa.dms.user.manage.orders.model;

import com.paa.dms.user.manage.orders.constants.MongoConstants;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = MongoConstants.MARKETPLACE_PRODUCTS_COLLECTION)
/**
 * Entity representing a product in the marketplace stored in MongoDB.
 * This class maps to the marketplace products collection in the database.
 */
public class MongoProductsEntity {
    /** Unique identifier for the order document in MongoDB. */
    private ObjectId _id;
    /** Color of the product. */
    private String color;
    /** Detailed description of the product. */
    private String description;
    /** Gender of the user or product target group (e.g., "Male", "Female", "Unisex"). */
    private  String gender;
    /** Identifier for the product's image in the system. */
    private String imageId;
    /** Name of the product. */
    private String name;
    /** Unique identifier for the product. */
    private String productId;
    /** The type or category of the product (e.g., pants, shoes). */
    private String productType;
    /** Size of the product (if applicable). */
    private String size;
    /** Price of the product. */
    private String price;

}
