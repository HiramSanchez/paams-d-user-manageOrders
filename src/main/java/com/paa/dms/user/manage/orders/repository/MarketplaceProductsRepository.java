package com.paa.dms.user.manage.orders.repository;

import com.paa.dms.user.manage.orders.model.MongoProductsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * Repository interface for managing products in MongoDB.
 * Extends MongoRepository to leverage Spring Data MongoDB functionalities.
 */
public interface MarketplaceProductsRepository extends MongoRepository<MongoProductsEntity, String> {

}
