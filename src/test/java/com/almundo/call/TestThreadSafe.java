package com.almundo.call;

import static com.almundo.call.util.TimeOut.setRandomTimeOut;

import java.util.ArrayList;
import java.util.List;

import com.almundo.call.controller.Dispatcher;
import com.almundo.call.entities.Call;
import com.almundo.call.entities.Director;
import com.almundo.call.entities.Employee;
import com.almundo.call.entities.Operator;
import com.almundo.call.entities.Supervisor;

/** Clase encargada de probabar la entranda simultanea de llamadas.
 * 
 * 
 * @author pablo.paparini */
public class TestThreadSafe extends Thread {
  
  private volatile static Object lock = new Object();

  public static Dispatcher dispatcher;

  public TestThreadSafe() {

    start();
  }

  public void run() {
  
	synchronized(lock) {
		try {
			lock.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  
	  
    for (int i = 0; i < 1; i++) {
      dispatcher.acceptIncommingCall(new Call("Customer"));
    }

  }

  public static void main(String[] args) {


    List<Employee> attenders = new ArrayList<Employee>();
    attenders.add(new Operator("Peter"));
    attenders.add(new Operator("Steve"));
    attenders.add(new Operator("Vauhn"));
    attenders.add(new Operator("Jon"));
    attenders.add(new Operator("Silvia"));
    attenders.add(new Supervisor("Slevin"));
    attenders.add(new Supervisor("Andy"));
    attenders.add(new Director("Alfred"));

    dispatcher = new Dispatcher(attenders, 10, 20);

    dispatcher.start();


    for (int i = 0; i < 10; i++) {
      new TestThreadSafe();
    }
    
    setRandomTimeOut(10);
    
    System.out.println("Se lanza");
    
    synchronized (lock) {
		lock.notifyAll();
	}

    setRandomTimeOut(5);

    Call call = new Call("Customer LAST");
    call.setLastCall(true);
    dispatcher.acceptIncommingCall(call);

    dispatcher.stopDispatching();


  }
}
