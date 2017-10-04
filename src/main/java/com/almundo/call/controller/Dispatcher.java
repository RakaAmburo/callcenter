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

  private BlockingQueue<Employee> attenders;
  private BlockingQueue<Call> calls;
  private ExecutorService callProcessing;
  private boolean lastCall = false;


  public Dispatcher(List<Employee> attenders, int concurrentCalls, int availableLines) {

    this.attenders = new PriorityBlockingQueue<Employee>();
    this.attenders.addAll(attenders);
    this.callProcessing = Executors.newFixedThreadPool(concurrentCalls);
    this.calls = new ArrayBlockingQueue<Call>(availableLines);
  }

  /** Metodo encargado de recibir las llamadas entrantes. Si hay lineas disponibles las encola para
   * ser despachadas ni bien se encuentre un asistente libre.
   * 
   * @param call */
  public void acceptIncommingCall(Call call) {

    if (calls.offer(call)) {
      System.out.println("call added to queue, automatic response asking to wait");
    } else {
      System.out.println("No line available, please call later.");
    }

  }

  private void dispatchCall() throws InterruptedException {
    Call call = calls.take();
    Employee employee = attenders.take();
    call.setAttendant(employee);
    call.setDispatchQueue(attenders);
    callProcessing.execute(call);
    if (call.isLastCall()) {
      lastCall = true;
    }
  }

  public void run() {

    try {

      while (true) {
        dispatchCall();
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

    callProcessing.shutdown();
    System.out.println("Call Center is shutting down.");

    try {
      callProcessing.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
      this.interrupt();
    } catch (InterruptedException e) {
      System.out.println("todo");
    }

  }

}
