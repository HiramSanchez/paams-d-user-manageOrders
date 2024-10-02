package com.paa.dms.user.manage.orders.model;

import com.paa.dms.user.manage.orders.constants.MongoConstants;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = MongoConstants.USER_ORDERS_COLLECTION)
public class MongoOrdersEntity {
    private ObjectId _id;
    private String orderDate;
    private String orderID;
    private String uid;
    private String guideNumber;
    private String orderStatus;
    private String orderTotal;
    private List<ProductEntity> orderList;
}
