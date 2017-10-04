package com.almundo.call.controller;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.almundo.call.entities.Call;
import com.almundo.call.entities.Employee;

public class Dispatcher extends Thread {

  private BlockingQueue<Employee> attenders = new PriorityBlockingQueue<Employee>();
  private BlockingQueue<Call> calls = new ArrayBlockingQueue<Call>(100);

  private ExecutorService callProcessing;

  private int attendersCount;

  private boolean lastCall = false;


  public Dispatcher(List<Employee> attenders, int concurrentCalls) {
    this.attenders.addAll(attenders);
    callProcessing = Executors.newFixedThreadPool(10);
    this.attendersCount = attenders.size();
  }

  public void dispatchCall(Call call) {

    if (calls.offer(call)) {
      System.out.println("call added to queue, automatic response asking to wait");
    } else {
      System.out.println("No line available");
    }

  }

  public void run() {

    try {

      while (true) {
        Call call = calls.take();
        Employee employee = attenders.take();
        call.setAttendant(employee);
        call.setDispatchQueue(attenders);
        callProcessing.execute(call);
        if (call.isLastCall()) {
          lastCall = true;
        }
      }

    } catch (InterruptedException e) {
      // TODO: handle exception
    }


  }

  public void stopDispatching() {


    while (this.lastCall == false) {
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    System.out.println("cerrando executor");
    callProcessing.shutdown();

    try {
      callProcessing.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
      this.interrupt();
    } catch (InterruptedException e) {
      System.out.println("todo");
    }

  }

}
