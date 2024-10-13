package com.paa.dms.user.manage.orders.service;

import com.paa.dms.user.manage.orders.model.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public interface UserManageOrdersService {
    ResponseEntity<String> saveOrder(HttpHeaders httpHeaders, RequestNewOrderEntity userRequest);
    ResponseEntity<ResponseRetrieveOrderEntity> findUserOrders(HttpHeaders httpHeaders);
    ResponseEntity<String> updateOrder(RequestChangeOrderEntity userRequest, HttpHeaders httpHeaders);
    ResponseEntity<String> deleteOrder(RequestDeleteOrderEntity userRequest, HttpHeaders httpHeaders);
}
