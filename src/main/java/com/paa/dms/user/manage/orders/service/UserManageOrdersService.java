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
import org.springframework.http.HttpStatus;
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

    ////////////////////////////
    //  Create Order Service  //
    ////////////////////////////
    public ResponseEntity<String> saveOrder(HttpHeaders httpHeaders, RequestNewOrderEntity userRequest) {
        log.debug("REQUEST >>> " + userRequest.toString());
        String uid = httpHeaders.getFirst("uid").toString();

        MongoOrdersEntity userOrder = new MongoOrdersEntity();
        userOrder.setUid(uid);
        userOrder.setGuideNumber("");
        userOrder.setOrderTotal(userRequest.getOrderTotal());
        userOrder.setOrderList(userRequest.getOrderList());
        userOrder.setOrderStatus(APIConstants.STATUS_ORDER_PLACED);
        userOrder.setOrderDate(utilTools.getDateFormatted());
        userOrder.setOrderID(utilTools.getOrderId());

        usersOrdersRepository.save(userOrder);
        ResponseEntity response = ResponseEntity.ok("Order Created");
        log.debug("Order Created");
        log.debug(APIConstants.RESPONSE_STRING_HTTP_EMPTY + response.getStatusCode());
        return response;
    }


    ////////////////////////////
    //   Read Order Service   //
    ////////////////////////////
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

    public List<OrderEntity> findUserOrdersByUid(String uid) {
        return usersOrdersRepository.findOrderByUid(uid);
    }


    ////////////////////////////
    //  Cancel Order Service  //
    ////////////////////////////
    public ResponseEntity<String> cancelOrder(RequestCancelOrderEntity userRequest, HttpHeaders httpHeaders) {

        String uid = httpHeaders.getFirst("uid").toString();
        log.debug("REQUEST >>> " + uid + " requested to cancel order number #" + userRequest.getOrderID());

        MongoOrdersEntity storedOrderData = findOrderByOrderId(userRequest.getOrderID()).orElseThrow(() -> new NoDataFoundException());
        String status = storedOrderData.getOrderStatus().toString();
        if ((status.equals(APIConstants.STATUS_ORDER_PLACED))&&(uid.equals(storedOrderData.getUid()))) {
            storedOrderData.setOrderStatus("Canceled");
            usersOrdersRepository.save(storedOrderData);
            ResponseEntity response = ResponseEntity.ok("Order was canceled successfully");
            log.debug(APIConstants.RESPONSE_STRING_HTTP_EMPTY + response.getStatusCode());
            return response;
        }else{
            throw new ForbiddenException();
        }
    }


    ////////////////////////////
    //  Delete Order Service  //
    ////////////////////////////
    public ResponseEntity<String> deleteOrder(RequestCancelOrderEntity userRequest, HttpHeaders httpHeaders) {

        String uid = httpHeaders.getFirst("uid").toString();
        log.debug("REQUEST >>> " + uid + " requested to delete order number #" + userRequest.getOrderID());

        MongoOrdersEntity storedOrderData = findOrderByOrderId(userRequest.getOrderID()).orElseThrow(() -> new NoDataFoundException());
        if (uid.equals(storedOrderData.getUid())) {
            usersOrdersRepository.deleteById(storedOrderData.get_id().toString());
            ResponseEntity response = ResponseEntity.ok("Order deleted successfully");
            log.debug(APIConstants.RESPONSE_STRING_HTTP_EMPTY + response.getStatusCode());
            return response;
        }else{
            throw new ForbiddenException();
        }
    }

    public Optional<MongoOrdersEntity> findOrderByOrderId(String orderID) {
        return usersOrdersRepository.findOrderByOrderId(orderID);
    }
}
