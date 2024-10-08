package com.paa.dms.user.manage.orders.controller;

import com.paa.dms.user.manage.orders.constants.APIConstants;
import com.paa.dms.user.manage.orders.model.RequestCancelOrderEntity;
import com.paa.dms.user.manage.orders.model.RequestNewOrderEntity;
import com.paa.dms.user.manage.orders.model.ResponseRetrieveOrderEntity;
import com.paa.dms.user.manage.orders.service.UserManageOrdersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation( //swagger config
            summary = "Create a new order",
            description = "Creates a new order based on the provided details.",
            parameters = {
                    @Parameter(in = ParameterIn.HEADER,name = "uid", description = "Header containing user id, string of 9 digit number", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Order details for the new order", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order created"),
                    @ApiResponse(responseCode = "400", description = "{field} + {validation error details}"),
                    @ApiResponse(responseCode = "500", description = "An unexpected error occurred")
            }
    )
    @PostMapping(path = APIConstants.CREATE_NEW_ORDER_ENDPOINT)
    public ResponseEntity<String> createOrder(@RequestHeader HttpHeaders httpHeaders,
                                              @Valid @RequestBody RequestNewOrderEntity userRequest) {
        log.debug(apiConstants.getLOG_NEW_ORDER_ENDPOINT());
        return userManageOrderService.saveOrder(httpHeaders,userRequest);
    }

    /**
     * Endpoint for retrieving all orders for the authenticated user.
     *
     * @param httpHeaders Headers containing user information (uid)
     * @return ResponseEntity with a list of user's orders
     */
    @Operation( //swagger config
            summary = "Retrieve user orders",
            description = "Fetches user orders using the provided headers containing user identification.",
            parameters = {
                    @Parameter(in = ParameterIn.HEADER,name = "uid", description = "Header containing user id, string of 9 digit number", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Orders data retrieve"),
                    @ApiResponse(responseCode = "404", description = "Resource not found in DB"),
                    @ApiResponse(responseCode = "500", description = "An unexpected error occurred")
            }
    )
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
    @Operation( //swagger config
            summary = "Cancel user order",
            description = "Updates user order to 'canceled' only if order is on 'orderPlaced' status",
            parameters = {
                    @Parameter(in = ParameterIn.HEADER,name = "uid", description = "Header containing user id, string of 9 digit number", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Id of the order to cancel", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order was canceled successfully"),
                    @ApiResponse(responseCode = "400", description = "{field} + {validation error details}"),
                    @ApiResponse(responseCode = "404", description = "Resource not found in DB"),
                    @ApiResponse(responseCode = "500", description = "An unexpected error occurred")
            }
    )
    @PutMapping(path = APIConstants.CANCEL_USER_ORDER_ENDPOINT)
    public ResponseEntity<String> cancelOrder(@Valid @RequestBody RequestCancelOrderEntity userRequest,
                                              @RequestHeader HttpHeaders httpHeaders) {
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
    @Operation( //swagger config
            summary = "Delete user order",
            description = "Permanently deletes an order based on the order id provided.",
            parameters = {
                    @Parameter(in = ParameterIn.HEADER,name = "uid", description = "Header containing user id, string of 9 digit number", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Id of the order to delete", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order deleted successfully"),
                    @ApiResponse(responseCode = "400", description = "{field} + {validation error details}"),
                    @ApiResponse(responseCode = "403", description = "Invalid Request due to data validation"),
                    @ApiResponse(responseCode = "404", description = "Resource not found in DB"),
                    @ApiResponse(responseCode = "500", description = "An unexpected error occurred")
            }
    )
    @DeleteMapping(path = APIConstants.DELETE_USER_ORDER_ENDPOINT)
    public ResponseEntity<String> deleteOrder(@Valid @RequestBody RequestCancelOrderEntity userRequest,
                                              @RequestHeader HttpHeaders httpHeaders) {
        log.debug(apiConstants.getLOG_DELETE_ORDER_ENDPOINT());
        return userManageOrderService.deleteOrder(userRequest,httpHeaders);
    }

}
