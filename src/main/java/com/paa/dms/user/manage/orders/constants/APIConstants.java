package com.paa.dms.user.manage.orders.constants;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class APIConstants {

    //Externalized endpoint paths
    public static final String BASE_PATH = "${constants.api.uri.basePath}";
    public static final String CREATE_NEW_ORDER_ENDPOINT = "${constants.api.uri.userNewOrder.path}";
    public static final String READ_USER_ORDERS_ENDPOINT = "${constants.api.uri.retrieveUserOrder.path}";
    public static final String DELETE_USER_ORDER_ENDPOINT = "${constants.api.uri.deleteOrderRequest.path}";
    public static final String CANCEL_USER_ORDER_ENDPOINT = "${constants.api.uri.cancelOrderRequest.path}";

    //Service call log messages
    @Value("${service.api.name}")
    private String SERVICE_NAME;
    @Value("${constants.api.uri.userNewOrder.call}")
    private String LOG_NEW_ORDER_ENDPOINT;
    @Value("${constants.api.uri.retrieveUserOrder.call}")
    private String LOG_READ_ORDER_ENDPOINT;
    @Value("${constants.api.uri.deleteOrderRequest.call}")
    private String LOG_DELETE_ORDER_ENDPOINT;
    @Value("${constants.api.uri.cancelOrderRequest.call}")
    private String LOG_CANCEL_ORDER_ENDPOINT;
    @Value("${constants.api.uri.status.orderPlaced}")
    private String STATUS_ORDER_PLACED;

    //Error resolver log messages
    @Value("${constants.api.uri.errors.msg.unexpected}")
    private String EXCEPTION_MSG_UNEXPECTED;
    @Value("${constants.api.uri.errors.msg.noDataFound}")
    private String EXCEPTION_MSG_NO_DATA_FOUND;
    @Value("${constants.api.uri.errors.msg.forbidden}")
    private String EXCEPTION_MSG_FORBIDDEN;









}
