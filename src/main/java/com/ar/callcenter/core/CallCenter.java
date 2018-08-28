package com.ar.callcenter.core;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.ar.callcenter.entities.Call;
import com.ar.callcenter.entities.Employee;

public class CallCenter {

	private BlockingQueue<Employee> attenders;
	private BlockingQueue<Call> availableLines;
	private ExecutorService callsInExecution;
	private Tasks task = new Tasks();
	private ScheduledExecutorService dispatcherExecutor = Executors.newSingleThreadScheduledExecutor();
	private ScheduledExecutorService shutDown = Executors.newSingleThreadScheduledExecutor();
	private Dispatcher disp;
	private int attrsCount = 0;
	private int rejectedCalls = 0;

	public CallCenter(List<Employee> attdrs, int avLinesSize) {

		System.out.println("Call Center Starting Up...");
		this.attrsCount = attdrs.size();
		this.attenders = new PriorityBlockingQueue<Employee>();
		this.attenders.addAll(attdrs);
		this.callsInExecution = Executors.newFixedThreadPool(this.attrsCount);
		this.availableLines = new ArrayBlockingQueue<Call>(avLinesSize);
		
		this.disp = new Dispatcher(this);
		dispatcherExecutor.scheduleAtFixedRate(task.dispatchCalls(disp), 0, 1, TimeUnit.MILLISECONDS);

	}

	public void acceptIncommingCall(Call call) {

		if (availableLines.offer(call)) {
			System.out.println("call added to queue, automatic response asking to wait");
		} else {
			System.out.println("No line available, please call later.");
			rejectedCalls++;
		}

	}

	public Call takeNextCall() throws InterruptedException {
		return availableLines.take();
	}

	public Employee takeFreeAttender() throws InterruptedException {
		return this.attenders.take();
	}

	public void processCall(Call call) {
		callsInExecution.execute(task.callProcessor(call, this));
	}

	public void returnAttender(Employee at) {
		this.attenders.add(at);
	}
	
	public int getFreeAttdrsCount() {
		return this.attenders.size();
	}
	
	public int getOnHoldSize() {
		return availableLines.size();
	}
	
	public void initShutDownProcess() {
		shutDown.scheduleAtFixedRate(task.shutDownProcess(this), 2, 5, TimeUnit.SECONDS);
	}
	
	public void shutDownSystem() {
		shutDown.shutdownNow();
		System.out.println("Interupting take lock:");
		dispatcherExecutor.shutdownNow();
		callsInExecution.shutdown();	
	    System.out.println("Call Center is shutting down.");
	    System.out.println(disp.getCallsCount() + " calls have been processed");
	    System.out.println(rejectedCalls + " calls have been rejected");
	   
	}
	
	public int getAttrsCount() {
		return attrsCount;
	}

}
