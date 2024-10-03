package com.paa.dms.user.manage.orders.constants;

public class MongoConstants {
    //Collections
    public static final String USER_ORDERS_COLLECTION = "users_orders";
    public static final String MARKETPLACE_PRODUCTS_COLLECTION = "marketplace_products";

    //Querys
    public static final String FIND_BY_UID_QUERY = "{uid:'?0'}";
    public static final String FIND_BY_ORDER_ID_QUERY = "{orderID:'?0'}";
}
