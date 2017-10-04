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
    Call call = new Call("Customer I");
    call.setLastCall(true);
    dispatcher.acceptIncommingCall(call);


    dispatcher.stopDispatching();

  }

}
