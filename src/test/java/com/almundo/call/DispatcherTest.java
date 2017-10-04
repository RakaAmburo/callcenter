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

public class DispatcherTest extends TestCase {

  public DispatcherTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(DispatcherTest.class);
  }


  /** Prueba del dispatcher para mas de 10 llamadas. */
  public void testDispatcher() {


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
    dispatcher.acceptIncommingCall(new Call("Customer K"));
    dispatcher.acceptIncommingCall(new Call("Customer L"));
    dispatcher.acceptIncommingCall(new Call("Customer M"));
    dispatcher.acceptIncommingCall(new Call("Customer N"));
    dispatcher.acceptIncommingCall(new Call("Customer O"));
    dispatcher.acceptIncommingCall(new Call("Customer P"));
    dispatcher.acceptIncommingCall(new Call("Customer Q"));
    dispatcher.acceptIncommingCall(new Call("Customer R"));
    dispatcher.acceptIncommingCall(new Call("Customer S"));
    Call call = new Call("Customer I");
    call.setLastCall(true);
    dispatcher.acceptIncommingCall(call);


    dispatcher.stopDispatching();

    assertTrue(true);
  }

}
