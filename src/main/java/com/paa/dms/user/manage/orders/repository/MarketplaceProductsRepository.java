package com.paa.dms.user.manage.orders.repository;

import com.paa.dms.user.manage.orders.model.MongoProductsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MarketplaceProductsRepository extends MongoRepository<MongoProductsEntity, String> {

}
