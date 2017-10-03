package com.almundo.call.controller;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

import com.almundo.call.entities.Call;
import com.almundo.call.entities.Director;
import com.almundo.call.entities.Employee;
import com.almundo.call.entities.Operator;
import com.almundo.call.entities.Supervisor;

public class Dispatcher {

  private BlockingQueue<Employee> dispatchQueue = new PriorityBlockingQueue<Employee>();
  private BlockingQueue<Call> calls = new PriorityBlockingQueue<Call>();

  private ExecutorService callInProcess = Executors.newFixedThreadPool(10);

  public void dispatchCall(Call call) {

    if (calls.offer(call)) {
      System.out.println("call added to queue, automatic response to wait");
    } else {
      System.out.println("No line available");
    }

  }

  public void run() throws InterruptedException {

    while (true) {
      Call call = calls.take();
      Employee employee = dispatchQueue.take();
      call.setAttendant(employee);
      call.setDispatchQueue(dispatchQueue);
      callInProcess.execute(call);
    }

  }

  public static void main(String[] args) {

    Dispatcher d = new Dispatcher();
    d.dispatchQueue.add(new Director());
    d.dispatchQueue.add(new Operator());
    d.dispatchQueue.add(new Supervisor());
    d.dispatchQueue.add(new Operator());
    d.dispatchQueue.add(new Supervisor());
    d.dispatchQueue.add(new Operator());

    while (!d.dispatchQueue.isEmpty()) {
      System.out.println(d.dispatchQueue.poll());
    }

  }


}
