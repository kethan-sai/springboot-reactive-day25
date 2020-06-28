package com.spring.reactive.example.reactivemongodb.service;

import com.spring.reactive.example.reactivemongodb.model.Inventory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IInventoryService {

	void create(Inventory i);

	Mono<Inventory> findById(Integer id);

	Flux<Inventory> findByName(String name);

	Flux<Inventory> findAll();

	Mono<Inventory> update(Inventory e);

	Mono<Void> delete(Integer id);

}
