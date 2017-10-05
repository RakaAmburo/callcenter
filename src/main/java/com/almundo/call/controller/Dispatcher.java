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

/** Clase controladora principial para las llamadas entrantes. Las llamadas son encoladas en un
 * blocking queue y a medida que hay asistentes disponibles son puleadas.
 * 
 * @author pablo.paparini */
public class Dispatcher extends Thread {

  private BlockingQueue<Employee> attenders;
  private BlockingQueue<Call> calls;
  private ExecutorService callProcessing;
  private boolean lastCall = false;
  private int callsCount = 0;

  private static final int SHUTDOWN_SLEEP = 500;


  /** Constructor del dispatcher instancia los blocking queues que contendran las llamadas y los
   * asistenes.
   * 
   * @param attdrs Lista de asistentes disponibles
   * @param conCalls Cantidad de llamadas que se pueden attender concurrentemente
   * @param availLines Cantidad de lineas en espera disponible */
  public Dispatcher(List<Employee> attdrs, int conCalls, int availLines) {

    this.attenders = new PriorityBlockingQueue<Employee>();
    this.attenders.addAll(attdrs);
    this.callProcessing = Executors.newFixedThreadPool(conCalls);
    this.calls = new ArrayBlockingQueue<Call>(availLines);
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


  /** Metodo despachador de llamadas. Gracias a los blocking queues (calls / attenders) queda a la
   * espera de que entren llamadas y posteriormente que se leibere un assitente para asignarselo.
   * Una vez asignado el assitente a la llamada, esa se encola en un executor que procesa
   * concurrentemente las llamadas.
   * 
   * @throws InterruptedException */
  private void dispatchCall() throws InterruptedException {
    Call call = calls.take();
    Employee employee = attenders.take();
    call.setAttendant(employee);
    call.setDispatchQueue(attenders);
    callProcessing.execute(call);
    callsCount++;
    if (call.isLastCall()) {
      lastCall = true;
    }
  }

  /** Metodo run del thread. */
  public void run() {

    try {

      while (!lastCall) {
        dispatchCall();
      }

    } catch (InterruptedException e) {
      e.printStackTrace();
    }


  }

  /** Metodo encargado de empezar el processo de apagado. espera a que la ultima llamada ingrese,
   * ordena apgar el executor. Y una vez que el executor temrmino de procesar todo ordena un
   * interrupt en caso de que el main thread siga vivo. */
  public void stopDispatching() {

    while (!this.lastCall) {
      try {
        Thread.sleep(SHUTDOWN_SLEEP);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    callProcessing.shutdown();
    System.out.println("Call Center is shutting down.");
    System.out.println(callsCount + " calls will be processed");

    try {
      callProcessing.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
      this.interrupt();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

}
