package com.ar.callcenter.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ar.callcenter.core.CallCenter;
import com.ar.callcenter.entities.Call;
import com.ar.callcenter.entities.Customer;
import com.ar.callcenter.entities.Director;
import com.ar.callcenter.entities.Employee;
import com.ar.callcenter.entities.Operator;
import com.ar.callcenter.entities.Supervisor;

@Service
public class CallCenterService {

	private CallCenter callCenter;

	public void status() {

	}

	public void start() {

		String operators = "Jesus,Joseph,Aline,Peter,Steve,Vauhn,Jon,Silvia,Ruperto";
		List<Employee> attenders = Arrays.stream(operators.split(",")).map(String::trim).map(a -> new Operator(a))
				.collect(Collectors.toList());

		attenders.add(new Supervisor("Slevin"));
		attenders.add(new Supervisor("Andy"));
		attenders.add(new Director("Alfred"));

		callCenter = new CallCenter(attenders, 50);

	}

	public void addCall(Call call) {
		callCenter.acceptIncommingCall(new Call(new Customer()));
	}

	public void dismissAttender() {

	}

	public void assignAttender(Employee emp) {

	}

	public void shutDown() {
		callCenter.initShutDownProcess();
	}

}
