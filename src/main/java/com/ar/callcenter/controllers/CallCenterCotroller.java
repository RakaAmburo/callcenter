package com.ar.callcenter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ar.callcenter.entities.Director;
import com.ar.callcenter.entities.Operator;
import com.ar.callcenter.entities.Supervisor;
import com.ar.callcenter.services.CallCenterService;


@RestController
public class CallCenterCotroller {

	@Autowired
	private CallCenterService service;

	@GetMapping("/shutDown")
	@ResponseStatus(HttpStatus.OK)
	public void shutDown() {
		service.shutDown();
	}
	
	@GetMapping("/start")
	@ResponseStatus(HttpStatus.OK)
	public void start() {
		service.start();
	}
	
	@GetMapping("/showStatus")
	@ResponseStatus(HttpStatus.OK)
	public void showStatus() {
		service.status();
	}
	
	@PostMapping("/attenders/addOperator")
    @ResponseStatus(HttpStatus.CREATED)
	public void addOperator (@RequestBody Operator op) {
    	service.assignAttender(op);
	}
	
	@PostMapping("/attenders/addSupervisor")
    @ResponseStatus(HttpStatus.CREATED)
	public void addSupervisor (@RequestBody Supervisor sup) {
    	service.assignAttender(sup);
	}
	
	@PostMapping("/attenders/addDirector")
    @ResponseStatus(HttpStatus.CREATED)
	public void addDirector (@RequestBody Director dir) {
    	service.assignAttender(dir);
	}
	
	@PostMapping("/addCall")
    @ResponseStatus(HttpStatus.CREATED)
	public void addCall () {
    	service.addCall(null);
	}
	

}
