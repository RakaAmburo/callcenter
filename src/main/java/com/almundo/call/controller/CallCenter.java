package com.almundo.call.controller;

import static com.almundo.call.util.TimeOut.setRandomTimeOut;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.almundo.call.entities.Call;
import com.almundo.call.entities.Director;
import com.almundo.call.entities.Employee;
import com.almundo.call.entities.Operator;
import com.almundo.call.entities.Supervisor;

public final class CallCenter {

	public static void main(String[] args) {

		String operators = "Jesus,Joseph,Aline,Peter,Steve,Vauhn,Jon,Silvia";
		List<Employee> attenders = Arrays.stream(operators.split(",")).map(String::trim).map(a -> new Operator(a))
				.collect(Collectors.toList());

		attenders.add(new Supervisor("Slevin"));
		attenders.add(new Supervisor("Andy"));
		attenders.add(new Director("Alfred"));

		Dispatcher dispatcher = new Dispatcher(attenders, 10, 15);

		dispatcher.start();
		
		IntStream.rangeClosed('A', 'Z').mapToObj(a -> (char)a).map(a -> "Customer " + a)
				.forEach(a -> {dispatcher.acceptIncommingCall(new Call(a)); setRandomTimeOut();});


		Call call = new Call("Customer LAST");
		call.setLastCall(true);
		dispatcher.acceptIncommingCall(call);

		setRandomTimeOut(2);

		dispatcher.stopDispatching();

	}

}
