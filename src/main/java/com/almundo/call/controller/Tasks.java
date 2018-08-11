package com.almundo.call.controller;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Tasks {
	
	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
		      public void run() {
		        // task to run goes here
		    	  try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        System.out.println("Hello !!");
		      }
		    };
		ScheduledExecutorService service = Executors
	            .newSingleThreadScheduledExecutor();
	         service.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
	}
	
	
	public Runnable callInProcess(String employee, String customer, String attenderPool) {
	    return () -> {
	        try {
	        	//Taking call from log or web output
				TimeUnit.MILLISECONDS.sleep(500);
				//Ending call log or web output
			} catch (InterruptedException e) {
				e.printStackTrace();//log
			} finally {
				//return the employe to the attenderPool
			}
	        
	    };
	}
	
	public Runnable dispatchCalls(String employee, String customer, String attenderPool) {
	    return () -> {
	        try {
	        	 while (true) {
	        	        throw new InterruptedException();
	        	      }
			} catch (InterruptedException e) {
				e.printStackTrace();//log
			}         
	    };
	}
	
	
	
}
