package com.ar.callcenter.entities;

import com.ar.callcenter.util.ShortUuid;

public class Customer {
	
	private String name;
	
	public Customer() {
		this.name = ShortUuid.shortUUID();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
