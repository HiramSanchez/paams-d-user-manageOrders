package com.paa.dms.user.manage.orders.repository;

import com.paa.dms.user.manage.orders.constants.MongoConstants;
import com.paa.dms.user.manage.orders.model.MongoOrdersEntity;
import com.paa.dms.user.manage.orders.model.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;
/**
 * Repository interface for managing user orders in MongoDB.
 * Extends MongoRepository to leverage Spring Data MongoDB functionalities.
 */
public interface UsersOrdersRepository extends MongoRepository<MongoOrdersEntity, String> {

    /**
     * Custom query to find orders associated with a specific user (UID).
     *
     * @param uid The user's unique identifier
     * @return A list of orders belonging to the user
     */
    @Query(value = MongoConstants.FIND_BY_UID_QUERY)
    List<OrderEntity> findOrderByUid(String uid);

    /**
     * Custom query to find a specific order by its order ID.
     *
     * @param orderId The unique identifier of the order
     * @return An Optional containing the found order, or empty if not found
     */
    @Query(value = MongoConstants.FIND_BY_ORDER_ID_QUERY)
    Optional<MongoOrdersEntity> findOrderByOrderId(String orderId);
}
