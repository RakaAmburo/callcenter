package com.almundo.call.controller;

import static com.almundo.call.util.TimeOut.setRandomTimeOut;

import java.util.ArrayList;
import java.util.List;

import com.almundo.call.entities.Call;
import com.almundo.call.entities.Director;
import com.almundo.call.entities.Employee;
import com.almundo.call.entities.Operator;
import com.almundo.call.entities.Supervisor;

public final class CallCenter {

  public static void main(String[] args) {

    List<Employee> attenders = new ArrayList<Employee>();

    attenders.add(new Operator("Jesus"));
    attenders.add(new Operator("Joseph"));
    attenders.add(new Operator("Aline"));
    attenders.add(new Operator("Peter"));

    attenders.add(new Operator("Steve"));
    attenders.add(new Operator("Vauhn"));
    attenders.add(new Operator("Jon"));
    attenders.add(new Operator("Silvia"));

    attenders.add(new Supervisor("Slevin"));
    attenders.add(new Supervisor("Andy"));

    attenders.add(new Director("Alfred"));

    Dispatcher dispatcher = new Dispatcher(attenders, 10, 15);

    dispatcher.start();

    for (char character = 'A'; character <= 'Z'; character++) {
      dispatcher.acceptIncommingCall(new Call("Customer " + character));
      setRandomTimeOut();
    }

    Call call = new Call("Customer LAST");
    call.setLastCall(true);
    dispatcher.acceptIncommingCall(call);

    setRandomTimeOut();
    setRandomTimeOut();


    dispatcher.stopDispatching();

  }

}
