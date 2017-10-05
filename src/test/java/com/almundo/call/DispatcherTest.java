package com.almundo.call;

import java.util.ArrayList;
import java.util.List;

import com.almundo.call.controller.Dispatcher;
import com.almundo.call.entities.Call;
import com.almundo.call.entities.Director;
import com.almundo.call.entities.Employee;
import com.almundo.call.entities.Operator;
import com.almundo.call.entities.Supervisor;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static com.almundo.call.util.TimeOut.setRandomTimeOut;

public class DispatcherTest extends TestCase {

  public DispatcherTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(DispatcherTest.class);
  }

  /** Prueba del dispatcher, 10 llamadas. */
  public void testDispatcher() {

    System.out.println();
    System.out.println();
    System.out.println("Prueba del dispatcher, 10 llamadas.");
    System.out.println();

    List<Employee> attenders = new ArrayList<Employee>();
    attenders.add(new Operator("Peter"));
    attenders.add(new Operator("Steve"));
    attenders.add(new Operator("Vauhn"));
    attenders.add(new Operator("Jon"));
    attenders.add(new Operator("Silvia"));
    attenders.add(new Supervisor("Slevin"));
    attenders.add(new Supervisor("Andy"));
    attenders.add(new Director("Alfred"));

    Dispatcher dispatcher = new Dispatcher(attenders, 10, 20);

    dispatcher.start();

    dispatcher.acceptIncommingCall(new Call("Customer A"));
    dispatcher.acceptIncommingCall(new Call("Customer B"));
    dispatcher.acceptIncommingCall(new Call("Customer C"));
    dispatcher.acceptIncommingCall(new Call("Customer D"));
    dispatcher.acceptIncommingCall(new Call("Customer E"));
    dispatcher.acceptIncommingCall(new Call("Customer F"));
    dispatcher.acceptIncommingCall(new Call("Customer G"));
    dispatcher.acceptIncommingCall(new Call("Customer H"));
    dispatcher.acceptIncommingCall(new Call("Customer J"));
    Call call = new Call("Customer I");
    call.setLastCall(true);
    dispatcher.acceptIncommingCall(call);


    dispatcher.stopDispatching();

    assertTrue(true);

  }

  /** Prueba del dispatcher para mas de 10 llamadas con tiempo entre llamadas. */
  public void testDispatcher2() {

    System.out.println();
    System.out.println();
    System.out.println("Prueba del dispatcher, mas de 10 llamadas con timeout entre llamadas");
    System.out.println();

    List<Employee> attenders = new ArrayList<Employee>();
    attenders.add(new Operator("Peter"));
    attenders.add(new Operator("Steve"));
    attenders.add(new Operator("Vauhn"));
    attenders.add(new Operator("Jon"));
    attenders.add(new Operator("Silvia"));
    attenders.add(new Supervisor("Slevin"));
    attenders.add(new Supervisor("Andy"));
    attenders.add(new Director("Alfred"));

    Dispatcher dispatcher = new Dispatcher(attenders, 10, 20);

    dispatcher.start();

    for (char character = 'A'; character <= 'Z'; character++) {
      dispatcher.acceptIncommingCall(new Call("Customer " + character));
      setRandomTimeOut();
    }

    Call call = new Call("Customer LAST");
    call.setLastCall(true);
    dispatcher.acceptIncommingCall(call);

    setRandomTimeOut();

    dispatcher.stopDispatching();

    assertTrue(true);
  }

}
