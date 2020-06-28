package com.spring.reactive.example.reactivemongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.reactive.example.reactivemongodb.model.Inventory;
import com.spring.reactive.example.reactivemongodb.service.InventoryService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;
	
	@RequestMapping(value = {"/create", "/"}, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void create(@RequestBody Inventory i ){
		inventoryService.create(i);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Mono<Inventory>> findById(@PathVariable("id") Integer id) {
        Mono<Inventory> e = inventoryService.findById(id);
        HttpStatus status = e != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Inventory>>(e, status);
    }
 
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    @ResponseBody
    public Flux<Inventory> findByName(@PathVariable("name") String name) {
        return inventoryService.findByName(name);
    }
 
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<Inventory> findAll() {
        Flux<Inventory> emps = inventoryService.findAll();
        return emps;
    }
 
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Mono<Inventory> update(@RequestBody Inventory e) {
        return inventoryService.update(e);
    }
 
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        inventoryService.delete(id).subscribe();
    }
 
}
