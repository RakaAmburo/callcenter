package com.almundo.call.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Call implements Runnable {

  // Time duration between 5 and 10 seconds
  private int Low = 5000;
  private int High = 10000;

  public static List<Call> callsInProcess = new ArrayList<Call>();

  private boolean lastCall = false;

  private Employee attendant;
  public BlockingQueue<Employee> dispatchQueue;

  private String customerName;



  public Call(String customerName) {
    super();
    this.customerName = customerName;
  }

  public void run() {

    Random r = new Random();

    int callTime = r.nextInt(High - Low) + Low;

    try {
      System.out.println("Taking call from " + this.customerName + " => " + attendant.getName()
          + " (" + attendant.getClass().getSimpleName() + ")");
      callsInProcess.add(this);
      Thread.sleep(callTime);
      System.out.println("Call ended for " + this.customerName + ", duration: "
          + TimeUnit.MILLISECONDS.toSeconds(callTime) + " Seconds.");
      callsInProcess.remove(this);
      dispatchQueue.add(attendant);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  public Employee getAttendant() {
    return attendant;
  }

  public void setAttendant(Employee attendant) {
    this.attendant = attendant;
  }

  public BlockingQueue<Employee> getDispatchQueue() {
    return dispatchQueue;
  }

  public void setDispatchQueue(BlockingQueue<Employee> dispatchQueue) {
    this.dispatchQueue = dispatchQueue;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public boolean isLastCall() {
    return lastCall;
  }

  public void setLastCall(boolean lastCall) {
    this.lastCall = lastCall;
  }



}
