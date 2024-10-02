package com.paa.dms.user.manage.orders.constants;

public class APIConstants {

    //Externalized endpoint paths
    public static final String BASE_PATH = "${constants.api.uri.basePath}";
    public static final String CREATE_NEW_ORDER_ENDPOINT = "${constants.api.uri.userNewOrder.path}";
    public static final String READ_USER_ORDERS_ENDPOINT = "${constants.api.uri.retrieveUserOrder.path}";
    public static final String DELETE_USER_ORDER_ENDPOINT = "${constants.api.uri.deleteOrderRequest.path}";
    public static final String CANCEL_USER_ORDER_ENDPOINT = "${constants.api.uri.cancelOrderRequest.path}";

    //API Values
    public static final String STATUS_ORDER_PLACED = "OrderPlaced";

    //Service call log messages
    public static final String SERVICE_START = "MS started : paams-d-userManageOrders";
    public static final String LOG_NEW_ORDER_ENDPOINT = "[Service endpoint Call - userNewOrder]";
    public static final String LOG_READ_ORDER_ENDPOINT = "[Service endpoint Call - retrieveUserOrders]";
    public static final String LOG_DELETE_ORDER_ENDPOINT = "[Service endpoint Call - deleteOrderRequest]";
    public static final String LOG_CANCEL_ORDER_ENDPOINT = "[Service endpoint Call - cancelOrderRequest]";

    //Error resolver log messages
    public static final String RESPONSE_STRING_HTTP_EMPTY = "RESPONSE >>> HTTP STATUS ";
    public static final String EXCEPTION_MSG_UNEXPECTED = "An unexpected error occurred";
    public static final String EXCEPTION_MSG_NO_DATA_FOUND = "Resource not found in DB";
    public static final String EXCEPTION_MSG_FORBIDDEN = "Invalid Request due to data validation";









}
