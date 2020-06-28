package com.spring.reactive.example.reactivemongodb.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Scope(scopeName = "request" , proxyMode = ScopedProxyMode.TARGET_CLASS)
@Document
public class Inventory {

	@Id
	int id;
	String Name;
	long descp;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public long getDescp() {
		return descp;
	}
	public void setDescp(long descp) {
		this.descp = descp;
	}
	
	@Override
	public String toString() {
		return "Inventory [id=" + id + ", Name=" + Name + ", descp=" + descp + "]";
	}
	
	
}
