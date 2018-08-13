package com.mio.callcenter.core;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.mio.callcenter.entities.Call;
import com.mio.callcenter.entities.Customer;
import com.mio.callcenter.entities.Director;
import com.mio.callcenter.entities.Employee;
import com.mio.callcenter.entities.Operator;
import com.mio.callcenter.entities.Supervisor;
import com.mio.callcenter.util.TimeOut;


public class SimpleTest {
	
	public static void main(String[] args) {
		
		String operators = "Jesus,Joseph,Aline,Peter,Steve,Vauhn,Jon,Silvia";
		List<Employee> attenders = Arrays.stream(operators.split(",")).map(String::trim).map(a -> new Operator(a))
				.collect(Collectors.toList());

		attenders.add(new Supervisor("Slevin"));
		attenders.add(new Supervisor("Andy"));
		attenders.add(new Director("Alfred"));
		
		CallCenter cc = new CallCenter(attenders, 10, 15);
		
		TimeOut to = new TimeOut(200, 1000);
		
		IntStream.rangeClosed('A', 'Z').mapToObj(a -> new Customer(String.valueOf((char)a)))
		.forEach(a -> {cc.acceptIncommingCall(new Call(a)); to.setRandomTimeOut();});
		
		
	}

}
