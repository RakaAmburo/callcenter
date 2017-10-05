package com.almundo.call.entities;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/** Clase encargada de manejar de forma liberada del thread principal la llamada en si. Las
 * llamadas. duran entre 5 y 10 segundos. Una vez terminada la llamada el assistene es devuelto al
 * blockingqueue.
 * 
 * @author pablo.paparini */
public class Call implements Runnable {

  // Time duration between 5 and 10 seconds
  private static final int LOW = 5000;
  private static final int HIGHT = 10000;

  private boolean lastCall = false;

  private Employee attendant;
  private BlockingQueue<Employee> dispatchQueue;

  private String customerName;



  /** constructor de la clase call.
   * 
   * @param custName Nombre del cliente */
  public Call(String custName) {
    super();
    this.customerName = custName;
  }

  /** Metodo run del thread. */
  public void run() {

    Random r = new Random();

    int callTime = r.nextInt(HIGHT - LOW) + LOW;

    try {
      System.out.println("Taking call from " + this.customerName + " => " + attendant.getName()
          + " (" + attendant.getClass().getSimpleName() + ")");
      Thread.sleep(callTime);
      System.out.println("Call ended for " + this.customerName + ", duration: "
          + TimeUnit.MILLISECONDS.toSeconds(callTime) + " Seconds.");
      dispatchQueue.add(attendant);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  /** @return */
  public Employee getAttendant() {
    return attendant;
  }

  /** @param attendant */
  public void setAttendant(Employee attendant) {
    this.attendant = attendant;
  }

  /** @return */
  public BlockingQueue<Employee> getDispatchQueue() {
    return dispatchQueue;
  }

  /** @param dispatchQueue */
  public void setDispatchQueue(BlockingQueue<Employee> dispatchQueue) {
    this.dispatchQueue = dispatchQueue;
  }

  /** @return */
  public String getCustomerName() {
    return customerName;
  }

  /** @param customerName */
  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  /** @return */
  public boolean isLastCall() {
    return lastCall;
  }

  /** @param lastCall */
  public void setLastCall(boolean lastCall) {
    this.lastCall = lastCall;
  }



}
