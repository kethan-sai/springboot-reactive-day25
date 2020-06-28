package com.spring.reactive.example.reactivemongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.reactive.example.reactivemongodb.dao.InventoryRepository;
import com.spring.reactive.example.reactivemongodb.model.Inventory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class InventoryService implements IInventoryService{

	@Autowired
	InventoryRepository inventoryRepo;
	
	@Override
	public void create(Inventory i) {
		inventoryRepo.save(i).subscribe();
		
	}

	@Override
	public Mono<Inventory> findById(Integer id) {
		// TODO Auto-generated method stub
		return inventoryRepo.findById(id);
	}

	@Override
	public Flux<Inventory> findByName(String name) {
		// TODO Auto-generated method stub
		return inventoryRepo.findByName(name);
	}

	@Override
	public Flux<Inventory> findAll() {
		// TODO Auto-generated method stub
		return inventoryRepo.findAll();
	}

	@Override
	public Mono<Inventory> update(Inventory e) {
		// TODO Auto-generated method stub
		return inventoryRepo.save(e);
	}

	@Override
	public Mono<Void> delete(Integer id) {
		// TODO Auto-generated method stub
		return inventoryRepo.deleteById(id);
	}

}
