package com.mio.callcenter.core;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

import com.mio.callcenter.entities.Call;
import com.mio.callcenter.entities.Employee;



public class CallCenter {

	private BlockingQueue<Employee> attenders;
	private BlockingQueue<Call> availableLines;
	private ExecutorService callInExecution;
	private Tasks task = new Tasks();
	
	
	public CallCenter(List<Employee> attdrs, int inExecSize, int avLinesSize) {
		
		 this.attenders = new PriorityBlockingQueue<Employee>();
		    this.attenders.addAll(attdrs);
		    this.callInExecution = Executors.newFixedThreadPool(inExecSize);
		    this.availableLines = new ArrayBlockingQueue<Call>(avLinesSize);
		    
	}
	
	public void acceptIncommingCall(Call call) {

	    if (availableLines.offer(call)) {
	      System.out.println("call added to queue, automatic response asking to wait");
	    } else {
	      System.out.println("No line available, please call later.");
	    }

	  }
	
	public Call takeNextCall() throws InterruptedException {
		return availableLines.take();
	}
	
	public Employee takeFreeAttender() throws InterruptedException{
		return this.attenders.take();
	}
	
	public void processCall(Call call) {
		callInExecution.execute(task.callProcessor(call, this));
	}
	
	public void returnAtender(Employee at) {
		this.attenders.add(at);
	}
	

}
