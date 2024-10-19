package com.paa.dms.user.manage.orders.service;

import com.paa.dms.user.manage.orders.constants.APIConstants;
import com.paa.dms.user.manage.orders.exception.custom.BadRequestException;
import com.paa.dms.user.manage.orders.exception.custom.ForbiddenException;
import com.paa.dms.user.manage.orders.exception.custom.NoDataFoundException;
import com.paa.dms.user.manage.orders.model.*;
import com.paa.dms.user.manage.orders.repository.UsersOrdersRepository;
import com.paa.dms.user.manage.orders.util.UtilTools;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserManageOrdersServiceImplTest {
    @Mock
    private UsersOrdersRepository usersOrdersRepository;
    @Mock
    private UtilTools utilTools;
    @Mock
    private APIConstants apiConstants;
    @InjectMocks
    private UserManageOrdersServiceImpl userManageOrdersService;
    private HttpHeaders httpHeaders;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        httpHeaders = new HttpHeaders();
        httpHeaders.set("uid", "199705039");
    }


    @Test
    void saveOrder_shouldSaveOrderAndReturnOkResponse() {
        // Arrange
        RequestNewOrderEntity orderRequest = new RequestNewOrderEntity();
        orderRequest.setOrderTotal("100.0");
        orderRequest.setOrderList(List.of());
        // Act
        when(utilTools.getDateFormatted()).thenReturn("2024-10-18");
        when(utilTools.getOrderId()).thenReturn("ORDER123");
        when(apiConstants.getSTATUS_ORDER_PLACED()).thenReturn("PLACED");
        ResponseEntity<String> response = userManageOrdersService.saveOrder(httpHeaders, orderRequest);
        // Assert
        verify(usersOrdersRepository).save(any(MongoOrdersEntity.class));
        assertEquals("Order Created", response.getBody());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void findUserOrders_shouldReturnOrdersForGivenUid() {
        // Arrange
        List<OrderEntity> orders = List.of(new OrderEntity());
        when(usersOrdersRepository.findOrderByUid("199705039")).thenReturn(orders);
        // Act
        ResponseEntity<ResponseRetrieveOrderEntity> response = userManageOrdersService.findUserOrders(httpHeaders);
        // Assert
        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void findUserOrders_shouldThrowNoDataFoundException() {
        // Arrange
        when(usersOrdersRepository.findOrderByUid("199705039")).thenReturn(List.of());
        // Assert
        assertThrows(NoDataFoundException.class, () -> {
            userManageOrdersService.findUserOrders(httpHeaders);
        });
    }

    @Test
    void updateOrder_shouldUpdateOrderStatus1Successfully() {
        // Arrange
        RequestChangeOrderEntity request = new RequestChangeOrderEntity();
        request.setOrderID("ORDER123");
        request.setState("1");  // Dispatch
        MongoOrdersEntity order = new MongoOrdersEntity();
        order.setOrderStatus("PLACED");
        when(usersOrdersRepository.findOrderByOrderId("ORDER123")).thenReturn(Optional.of(order));
        when(apiConstants.getSTATUS_DISPATCHED()).thenReturn("DISPATCHED");
        when(apiConstants.getSTATUS_ORDER_PLACED()).thenReturn("PLACED");
        when(utilTools.getGuideNumber("ORDER123")).thenReturn("GUIDE123");
        // Act
        ResponseEntity<String> response = userManageOrdersService.updateOrder(request, httpHeaders);
        // Assert
        assertEquals("Order was updated to DISPATCHED successfully", response.getBody());
        verify(usersOrdersRepository).save(order);
    }
    @Test
    void updateOrder_shouldUpdateOrderStatus2Successfully() {
        // Arrange
        RequestChangeOrderEntity request = new RequestChangeOrderEntity();
        request.setOrderID("ORDER123");
        request.setState("2");  // Dispatch
        MongoOrdersEntity order = new MongoOrdersEntity();
        order.setOrderStatus("DISPATCHED");
        when(usersOrdersRepository.findOrderByOrderId("ORDER123")).thenReturn(Optional.of(order));
        when(apiConstants.getSTATUS_SHIPPED()).thenReturn("SHIPPED");
        when(apiConstants.getSTATUS_DISPATCHED()).thenReturn("DISPATCHED");
        when(utilTools.getGuideNumber("ORDER123")).thenReturn("GUIDE123");
        // Act
        ResponseEntity<String> response = userManageOrdersService.updateOrder(request, httpHeaders);
        // Assert
        assertEquals("Order was updated to SHIPPED successfully", response.getBody());
        verify(usersOrdersRepository).save(order);
    }

    @Test
    void updateOrder_shouldUpdateOrderStatus3Successfully() {
        // Arrange
        RequestChangeOrderEntity request = new RequestChangeOrderEntity();
        request.setOrderID("ORDER123");
        request.setState("3");  // Dispatch
        MongoOrdersEntity order = new MongoOrdersEntity();
        order.setOrderStatus("SHIPPED");
        when(usersOrdersRepository.findOrderByOrderId("ORDER123")).thenReturn(Optional.of(order));
        when(apiConstants.getSTATUS_OUT_FOR_DELIVERY()).thenReturn("outForDelivery");
        when(apiConstants.getSTATUS_SHIPPED()).thenReturn("SHIPPED");
        when(utilTools.getGuideNumber("ORDER123")).thenReturn("GUIDE123");
        // Act
        ResponseEntity<String> response = userManageOrdersService.updateOrder(request, httpHeaders);
        // Assert
        assertEquals("Order was updated to outForDelivery successfully", response.getBody());
        verify(usersOrdersRepository).save(order);
    }

    @Test
    void updateOrder_shouldUpdateOrderStatus4Successfully() {
        // Arrange
        RequestChangeOrderEntity request = new RequestChangeOrderEntity();
        request.setOrderID("ORDER123");
        request.setState("4");  // Dispatch
        MongoOrdersEntity order = new MongoOrdersEntity();
        order.setOrderStatus("outForDelivery");
        when(usersOrdersRepository.findOrderByOrderId("ORDER123")).thenReturn(Optional.of(order));
        when(apiConstants.getSTATUS_DELIVERED()).thenReturn("delivered");
        when(apiConstants.getSTATUS_OUT_FOR_DELIVERY()).thenReturn("outForDelivery");
        when(utilTools.getGuideNumber("ORDER123")).thenReturn("GUIDE123");
        // Act
        ResponseEntity<String> response = userManageOrdersService.updateOrder(request, httpHeaders);
        // Assert
        assertEquals("Order was updated to delivered successfully", response.getBody());
        verify(usersOrdersRepository).save(order);
    }

    @Test
    void updateOrder_shouldUpdateOrderStatus5Successfully() {
        // Arrange
        RequestChangeOrderEntity request = new RequestChangeOrderEntity();
        request.setOrderID("ORDER123");
        request.setState("5");
        MongoOrdersEntity order = new MongoOrdersEntity();
        order.setOrderStatus("PLACED");
        when(usersOrdersRepository.findOrderByOrderId("ORDER123")).thenReturn(Optional.of(order));
        when(apiConstants.getSTATUS_CANCELED()).thenReturn("canceled");
        when(apiConstants.getSTATUS_ORDER_PLACED()).thenReturn("PLACED");
        when(utilTools.getGuideNumber("ORDER123")).thenReturn("GUIDE123");
        // Act
        ResponseEntity<String> response = userManageOrdersService.updateOrder(request, httpHeaders);
        // Assert
        assertEquals("Order was updated to canceled successfully", response.getBody());
        verify(usersOrdersRepository).save(order);
    }
    @Test
    void updateOrder_badRequest() {
        // Arrange
        RequestChangeOrderEntity request = new RequestChangeOrderEntity();
        request.setOrderID("ORDER123");
        request.setState("6");
        MongoOrdersEntity order = new MongoOrdersEntity();
        order.setOrderStatus("PLACED");
        when(usersOrdersRepository.findOrderByOrderId("ORDER123")).thenReturn(Optional.of(order));
        // Assert
        assertThrows(BadRequestException.class, () -> {
            userManageOrdersService.updateOrder(request, httpHeaders);
        });
    }

    @Test
    void updateOrder_shouldThrowForbiddenException() {
        // Arrange
        RequestChangeOrderEntity request = new RequestChangeOrderEntity();
        request.setOrderID("ORDER123");
        request.setState("2");  // Ship
        MongoOrdersEntity order = new MongoOrdersEntity();
        order.setOrderStatus("PLACED");
        // Act
        when(usersOrdersRepository.findOrderByOrderId("ORDER123")).thenReturn(Optional.of(order));
        when(apiConstants.getSTATUS_DISPATCHED()).thenReturn("DISPATCHED");
        // Assert
        assertThrows(ForbiddenException.class, () -> {
            userManageOrdersService.updateOrder(request, httpHeaders);
        });
    }

    @Test
    void deleteOrder_shouldDeleteOrderSuccessfully() {
        // Arrange
        String orderId = "605c72ef6e73b00b4b3c0e11";
        RequestDeleteOrderEntity userRequest = new RequestDeleteOrderEntity();
        userRequest.setOrderID(orderId);
        MongoOrdersEntity storedOrderData = new MongoOrdersEntity();
        storedOrderData.set_id(new ObjectId(orderId));
        when(usersOrdersRepository.findOrderByOrderId(orderId)).thenReturn(Optional.of(storedOrderData));
        // Act
        ResponseEntity<String> responseEntity = userManageOrdersService.deleteOrder(userRequest, httpHeaders);
        // Assert
        verify(usersOrdersRepository).deleteById(storedOrderData.get_id().toString());
        assertEquals(ResponseEntity.ok("Order deleted successfully"), responseEntity);
    }

    @Test
    void deleteOrder_shouldThrowNoDataFoundException() {
        // Arrange
        RequestDeleteOrderEntity request = new RequestDeleteOrderEntity();
        request.setOrderID("ORDER123");
        // Act
        when(usersOrdersRepository.findOrderByOrderId("ORDER123")).thenReturn(Optional.empty());
        // Assert
        assertThrows(NoDataFoundException.class, () -> {
            userManageOrdersService.deleteOrder(request, httpHeaders);
        });
    }

}