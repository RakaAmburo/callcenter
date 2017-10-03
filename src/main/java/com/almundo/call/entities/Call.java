package com.almundo.call.entities;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Call implements Runnable {

  // Time duration between 5 and 10 seconds
  private int Low = 5000;
  private int High = 10000;

  private Employee attendant;
  private BlockingQueue<Employee> dispatchQueue;



  public void run() {

    Random r = new Random();

    int callTime = r.nextInt(High - Low) + Low;

    try {
      Thread.sleep(callTime);
      System.out.println("end call " + TimeUnit.MILLISECONDS.toSeconds(callTime));
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

}
