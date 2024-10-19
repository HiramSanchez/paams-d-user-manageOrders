package com.paa.dms.user.manage.orders.controller;

import com.paa.dms.user.manage.orders.constants.APIConstants;
import com.paa.dms.user.manage.orders.model.RequestChangeOrderEntity;
import com.paa.dms.user.manage.orders.model.RequestDeleteOrderEntity;
import com.paa.dms.user.manage.orders.model.RequestNewOrderEntity;
import com.paa.dms.user.manage.orders.model.ResponseRetrieveOrderEntity;
import com.paa.dms.user.manage.orders.service.UserManageOrdersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserManageOrdersControllerTest {
    @InjectMocks
    private UserManageOrdersController userManageOrdersController;
    @Mock
    private UserManageOrdersService userManageOrderService;
    @Mock
    private APIConstants apiConstants;
    private HttpHeaders httpHeaders;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        httpHeaders = new HttpHeaders();
        httpHeaders.set("uid", "199705039");
    }
    @Test
    void testCreateOrder() {
        // Arrange
        RequestNewOrderEntity userRequest = new RequestNewOrderEntity();
        when(userManageOrderService.saveOrder(httpHeaders,userRequest)).thenReturn(ResponseEntity.ok("Order created"));
        ResponseEntity<String> response = userManageOrdersController.createOrder(httpHeaders,userRequest);
        // Assert
        verify(userManageOrderService, times(1)).saveOrder(httpHeaders, userRequest);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Order created", response.getBody());
    }
    @Test
    void testGetOrders() {
        // Arrange
        ResponseRetrieveOrderEntity ordersResponse = new ResponseRetrieveOrderEntity();
        when(userManageOrderService.findUserOrders(httpHeaders))
                .thenReturn(ResponseEntity.ok(ordersResponse));
        // Act
        ResponseEntity<ResponseRetrieveOrderEntity> response = userManageOrdersController.getOrders(httpHeaders);
        // Assert
        verify(userManageOrderService, times(1)).findUserOrders(httpHeaders);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(ordersResponse, response.getBody());
    }
    @Test
    void testCancelOrder() {
        // Arrange
        RequestChangeOrderEntity changeRequest = new RequestChangeOrderEntity();
        when(userManageOrderService.updateOrder(changeRequest, httpHeaders))
                .thenReturn(ResponseEntity.ok("Order status updated"));
        // Act
        ResponseEntity<String> response = userManageOrdersController.cancelOrder(changeRequest, httpHeaders);
        // Assert
        verify(userManageOrderService, times(1)).updateOrder(changeRequest, httpHeaders);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Order status updated", response.getBody());
    }
    @Test
    void testDeleteOrder() {
        // Arrange
        RequestDeleteOrderEntity deleteRequest = new RequestDeleteOrderEntity();
        when(userManageOrderService.deleteOrder(deleteRequest, httpHeaders))
                .thenReturn(ResponseEntity.ok("Order deleted"));
        // Act
        ResponseEntity<String> response = userManageOrdersController.deleteOrder(deleteRequest, httpHeaders);
        // Assert
        verify(userManageOrderService, times(1)).deleteOrder(deleteRequest, httpHeaders);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Order deleted", response.getBody());
    }

}