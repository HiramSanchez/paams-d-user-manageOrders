package com.paa.dms.user.manage.orders.service;

import com.paa.dms.user.manage.orders.constants.APIConstants;
import com.paa.dms.user.manage.orders.exception.custom.ForbiddenException;
import com.paa.dms.user.manage.orders.exception.custom.NoDataFoundException;
import com.paa.dms.user.manage.orders.model.*;
import com.paa.dms.user.manage.orders.repository.MarketplaceProductsRepository;
import com.paa.dms.user.manage.orders.repository.UsersOrdersRepository;
import com.paa.dms.user.manage.orders.util.UtilTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserManageOrdersService {
    @Autowired
    private MarketplaceProductsRepository marketplaceProductsRepository;
    @Autowired
    private UsersOrdersRepository usersOrdersRepository;
    @Autowired
    private UtilTools utilTools;
    @Autowired
    private APIConstants apiConstants;

    /**
     * Create Order Service
     * Creates a new order for the user and saves it to the database.
     * @param httpHeaders HTTP headers to retrieve the user identifier (uid)
     * @param userRequest the order details from the user's request
     * @return ResponseEntity confirming that the order was created
     */
    public ResponseEntity<String> saveOrder(HttpHeaders httpHeaders, RequestNewOrderEntity userRequest) {
        log.debug("REQUEST >>> " + userRequest.toString());
        String uid = httpHeaders.getFirst("uid").toString();

        MongoOrdersEntity userOrder = new MongoOrdersEntity();
        userOrder.setUid(uid);
        userOrder.setGuideNumber("");
        userOrder.setOrderTotal(userRequest.getOrderTotal());
        userOrder.setOrderList(userRequest.getOrderList());
        userOrder.setOrderStatus(apiConstants.getSTATUS_ORDER_PLACED());
        userOrder.setOrderDate(utilTools.getDateFormatted());
        userOrder.setOrderID(utilTools.getOrderId());

        usersOrdersRepository.save(userOrder);
        ResponseEntity response = ResponseEntity.ok("Order Created");
        log.debug("RESPONSE >>> " + response);
        return response;
    }

    /**
     * Read Order Service
     * Retrieves all orders for the user based on their uid.
     * @param httpHeaders HTTP headers to retrieve the user identifier (uid)
     * @return ResponseEntity containing a list of the user's orders
     */
    public ResponseEntity<ResponseRetrieveOrderEntity> findUserOrders(HttpHeaders httpHeaders) {

        String uid = httpHeaders.getFirst("uid").toString();
        log.debug("REQUEST >>> " + uid + " orders data requested");
        var userOrders = findUserOrdersByUid(uid);

        if (!userOrders.isEmpty()) {
            ResponseRetrieveOrderEntity response = new ResponseRetrieveOrderEntity();
            response.setOrders(userOrders);

            log.debug("RESPONSE >>> " + response);
            return ResponseEntity.ok(response);
        }
        throw new NoDataFoundException();
    }

    /**
     * Finds orders by the user's uid from the repository.
     * @param uid the user's identifier
     * @return List of orders associated with the uid
     */
    public List<OrderEntity> findUserOrdersByUid(String uid) {
        return usersOrdersRepository.findOrderByUid(uid);
    }

     /**
     * Cancel Order Service
     * Cancels an order if the user is authorized and the order status is "placed".
     * @param userRequest details of the order to cancel
     * @param httpHeaders HTTP headers to retrieve the user identifier (uid)
     * @return ResponseEntity confirming the order cancellation
     */
    public ResponseEntity<String> cancelOrder(RequestCancelOrderEntity userRequest, HttpHeaders httpHeaders) {

        String uid = httpHeaders.getFirst("uid").toString();
        log.debug("REQUEST >>> " + uid + " requested to cancel order number #" + userRequest.getOrderID());
        MongoOrdersEntity storedOrderData = findOrderByOrderId(userRequest.getOrderID()).orElseThrow(() -> new NoDataFoundException());
        String status = storedOrderData.getOrderStatus().toString();
        if ((status.equals(apiConstants.getSTATUS_ORDER_PLACED()))&&(uid.equals(storedOrderData.getUid()))) {
            storedOrderData.setOrderStatus("canceled");
            usersOrdersRepository.save(storedOrderData);
            ResponseEntity response = ResponseEntity.ok("Order was canceled successfully");
            log.debug("RESPONSE >>> " + response);
            return response;
        }else{
            throw new ForbiddenException();
        }
    }

    /**
     * Delete Order Service
     * Deletes an order if the user is authorized.
     * @param userRequest details of the order to delete
     * @param httpHeaders HTTP headers to retrieve the user identifier (uid)
     * @return ResponseEntity confirming the order deletion
     */
    public ResponseEntity<String> deleteOrder(RequestCancelOrderEntity userRequest, HttpHeaders httpHeaders) {

        String uid = httpHeaders.getFirst("uid").toString();
        log.debug("REQUEST >>> " + uid + " requested to delete order number #" + userRequest.getOrderID());

        MongoOrdersEntity storedOrderData = findOrderByOrderId(userRequest.getOrderID()).orElseThrow(() -> new NoDataFoundException());
        if (uid.equals(storedOrderData.getUid())) {
            usersOrdersRepository.deleteById(storedOrderData.get_id().toString());
            ResponseEntity response = ResponseEntity.ok("Order deleted successfully");
            log.debug("RESPONSE >>> " + response);
            return response;
        }else{
            throw new ForbiddenException();
        }
    }

    /**
     * Finds an order by its ID from the repository.
     * @param orderID the order identifier
     * @return Optional containing the order data if found
     */
    public Optional<MongoOrdersEntity> findOrderByOrderId(String orderID) {
        return usersOrdersRepository.findOrderByOrderId(orderID);
    }
}
