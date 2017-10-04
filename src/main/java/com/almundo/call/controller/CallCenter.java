package com.almundo.call.controller;

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
    attenders.add(new Operator("Peter"));
    attenders.add(new Operator("Steve"));
    attenders.add(new Operator("Vauhn"));
    attenders.add(new Operator("Jon"));
    attenders.add(new Operator("Silvia"));
    attenders.add(new Supervisor("Slevin"));
    attenders.add(new Supervisor("Andy"));
    attenders.add(new Director("Alfred"));

    Dispatcher dispatcher = new Dispatcher(attenders, 10);

    dispatcher.start();

    dispatcher.dispatchCall(new Call("Customer A"));
    dispatcher.dispatchCall(new Call("Customer B"));
    dispatcher.dispatchCall(new Call("Customer C"));
    dispatcher.dispatchCall(new Call("Customer D"));
    dispatcher.dispatchCall(new Call("Customer E"));
    dispatcher.dispatchCall(new Call("Customer F"));
    dispatcher.dispatchCall(new Call("Customer G"));
    dispatcher.dispatchCall(new Call("Customer H"));
    Call call = new Call("Customer I");
    call.setLastCall(true);
    dispatcher.dispatchCall(call);


    dispatcher.stopDispatching();

  }

}
