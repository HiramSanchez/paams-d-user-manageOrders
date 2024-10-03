package com.paa.dms.user.manage.orders.controller;

import com.paa.dms.user.manage.orders.constants.APIConstants;
import com.paa.dms.user.manage.orders.model.RequestCancelOrderEntity;
import com.paa.dms.user.manage.orders.model.RequestNewOrderEntity;
import com.paa.dms.user.manage.orders.model.ResponseRetrieveOrderEntity;
import com.paa.dms.user.manage.orders.service.UserManageOrdersService;
import jakarta.validation.Valid;
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
    @Autowired
    private APIConstants apiConstants;


    /**
     * Endpoint for creating a new user order.
     *
     * @param httpHeaders Headers containing user information (uid)
     * @param userRequest Request body containing order details
     * @return ResponseEntity with status 200 OK when the order is successfully created
     */
    @PostMapping(path = APIConstants.CREATE_NEW_ORDER_ENDPOINT)
    public ResponseEntity<String> createOrder(@RequestHeader HttpHeaders httpHeaders, @Valid @RequestBody RequestNewOrderEntity userRequest) {
        log.debug(apiConstants.getLOG_NEW_ORDER_ENDPOINT());
        return userManageOrderService.saveOrder(httpHeaders,userRequest);
    }

    /**
     * Endpoint for retrieving all orders for the authenticated user.
     *
     * @param httpHeaders Headers containing user information (uid)
     * @return ResponseEntity with a list of user's orders
     */
    @GetMapping(path = APIConstants.READ_USER_ORDERS_ENDPOINT)
    public ResponseEntity<ResponseRetrieveOrderEntity> getOrders(@RequestHeader HttpHeaders httpHeaders) {
        log.debug(apiConstants.getLOG_READ_ORDER_ENDPOINT());
        return userManageOrderService.findUserOrders(httpHeaders);
    }

    /**
     * Endpoint for canceling a specific user order.
     *
     * @param userRequest Request body containing the order ID to cancel
     * @param httpHeaders Headers containing user information (uid)
     * @return ResponseEntity with status 200 OK upon successful cancellation
     */
    @PutMapping(path = APIConstants.CANCEL_USER_ORDER_ENDPOINT)
    public ResponseEntity<String> cancelOrder(@RequestBody RequestCancelOrderEntity userRequest, @Valid @RequestHeader HttpHeaders httpHeaders) {
        log.debug(apiConstants.getLOG_CANCEL_ORDER_ENDPOINT());
        return userManageOrderService.cancelOrder(userRequest,httpHeaders);
    }

    /**
     * Endpoint for deleting a specific user order permanently.
     *
     * @param userRequest Request body containing the order ID to delete
     * @param httpHeaders Headers containing user information (uid)
     * @return ResponseEntity with status 200 OK upon successful deletion
     */
    @DeleteMapping(path = APIConstants.DELETE_USER_ORDER_ENDPOINT)
    public ResponseEntity<String> deleteOrder(@RequestBody RequestCancelOrderEntity userRequest, @Valid @RequestHeader HttpHeaders httpHeaders) {
        log.debug(apiConstants.getLOG_DELETE_ORDER_ENDPOINT());
        return userManageOrderService.deleteOrder(userRequest,httpHeaders);
    }

}
