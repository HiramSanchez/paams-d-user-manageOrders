package com.paa.dms.user.manage.orders.controller;

import com.paa.dms.user.manage.orders.constants.APIConstants;
import com.paa.dms.user.manage.orders.model.RequestCancelOrderEntity;
import com.paa.dms.user.manage.orders.model.RequestNewOrderEntity;
import com.paa.dms.user.manage.orders.model.ResponseRetrieveOrderEntity;
import com.paa.dms.user.manage.orders.service.UserManageOrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping(APIConstants.BASE_PATH)
public class UserManageOrdersController {

    @Autowired
    private UserManageOrdersService userManageOrderService;

    @PostMapping(path = APIConstants.CREATE_NEW_ORDER_ENDPOINT)
    public ResponseEntity<String> createOrder(@RequestHeader HttpHeaders httpHeaders, @RequestBody RequestNewOrderEntity userRequest) {
        log.debug(APIConstants.LOG_NEW_ORDER_ENDPOINT);
        return userManageOrderService.saveOrder(httpHeaders,userRequest);
    }

    @GetMapping(path = APIConstants.READ_USER_ORDERS_ENDPOINT)
    public ResponseEntity<ResponseRetrieveOrderEntity> getOrders(@RequestHeader HttpHeaders httpHeaders) {
        log.debug(APIConstants.LOG_READ_ORDER_ENDPOINT);
        return userManageOrderService.findUserOrders(httpHeaders);
    }

    @PutMapping(path = APIConstants.CANCEL_USER_ORDER_ENDPOINT)
    public ResponseEntity<String> cancelOrder(@RequestBody RequestCancelOrderEntity userRequest,@RequestHeader HttpHeaders httpHeaders) {
        log.debug(APIConstants.LOG_CANCEL_ORDER_ENDPOINT);
        return userManageOrderService.cancelOrder(userRequest,httpHeaders);
    }

    @DeleteMapping(path = APIConstants.DELETE_USER_ORDER_ENDPOINT)
    public ResponseEntity<String> deleteOrder(@RequestBody RequestCancelOrderEntity userRequest,@RequestHeader HttpHeaders httpHeaders) {
        log.debug(APIConstants.LOG_DELETE_ORDER_ENDPOINT);
        return userManageOrderService.deleteOrder(userRequest,httpHeaders);
    }

}
