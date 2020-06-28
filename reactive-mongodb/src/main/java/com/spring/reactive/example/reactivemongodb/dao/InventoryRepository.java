package com.spring.reactive.example.reactivemongodb.dao;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.spring.reactive.example.reactivemongodb.model.Inventory;

import reactor.core.publisher.Flux;

public interface InventoryRepository extends ReactiveMongoRepository<Inventory,Integer>{

	@Query("{ 'name': ?0 }")
    Flux<Inventory> findByName(final String name);
}
