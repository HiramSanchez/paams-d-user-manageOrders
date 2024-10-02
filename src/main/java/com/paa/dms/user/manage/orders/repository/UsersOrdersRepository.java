package com.paa.dms.user.manage.orders.repository;

import com.paa.dms.user.manage.orders.constants.MongoConstants;
import com.paa.dms.user.manage.orders.model.MongoOrdersEntity;
import com.paa.dms.user.manage.orders.model.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsersOrdersRepository extends MongoRepository<MongoOrdersEntity, String> {
    @Query(value = MongoConstants.FIND_BY_UID_QUERY)
    List<OrderEntity> findOrderByUid(String uid);

    @Query(value = MongoConstants.FIND_BY_ORDER_ID_QUERY)
    Optional<MongoOrdersEntity> findOrderByOrderId(String orderId);
}
