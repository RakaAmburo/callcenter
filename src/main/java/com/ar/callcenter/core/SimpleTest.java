package com.ar.callcenter.core;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.ar.callcenter.entities.Call;
import com.ar.callcenter.entities.Customer;
import com.ar.callcenter.entities.Director;
import com.ar.callcenter.entities.Employee;
import com.ar.callcenter.entities.Operator;
import com.ar.callcenter.entities.Supervisor;
import com.ar.callcenter.util.TimeOut;


public class SimpleTest {
	
	public static void main(String[] args) {
		
		String operators = "Jesus,Joseph,Aline,Peter,Steve,Vauhn,Jon,Silvia,Ruperto";
		List<Employee> attenders = Arrays.stream(operators.split(",")).map(String::trim).map(a -> new Operator(a))
				.collect(Collectors.toList());

		attenders.add(new Supervisor("Slevin"));
		attenders.add(new Supervisor("Andy"));
		attenders.add(new Director("Alfred"));
		
		CallCenter cc = new CallCenter(attenders, 15);
		
		TimeOut to = new TimeOut(200, 1000);
		
		System.out.println("Number of calls: " + 30);
		
		IntStream.rangeClosed(1, 30).mapToObj(a -> new Customer())
		.forEach(a -> {cc.acceptIncommingCall(new Call(a)); to.setRandomTimeOut();});
		
		cc.initShutDownProcess();
		
	}

}
