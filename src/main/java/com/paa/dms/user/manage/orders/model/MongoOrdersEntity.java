package com.paa.dms.user.manage.orders.model;

import com.paa.dms.user.manage.orders.constants.MongoConstants;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = MongoConstants.USER_ORDERS_COLLECTION)
/**
 * Entity representing an order stored in MongoDB.
 * This class maps to the user orders collection in the database.
 */
public class MongoOrdersEntity {

     /** Unique identifier for the order document in MongoDB. */
    private ObjectId _id;
    /** The unique identifier (UID) of the user who placed the order. */
    private String uid;
    /** The date when the order was placed, formatted as a String. */
    private String orderDate;
    /** Unique identifier for the order. */
    private String orderID;
    /** Guide or tracking number associated with the delivery. */
    private String guideNumber;
    /** Status of the order (e.g.,orderPlaced, canceled). */
    private String orderStatus;
    /** Total amount for the order, stored as a String. */
    private String orderTotal;
    /** List of products associated with the order. */
    private List<ProductEntity> orderList;
}
