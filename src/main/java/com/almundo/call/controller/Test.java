package com.almundo.call.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.almundo.call.entities.Call;

public class Test {

  public static void main(String[] args) {

    ExecutorService service = Executors.newFixedThreadPool(5);

    service.execute(new Call());
    service.execute(new Call());
    service.execute(new Call());
    service.execute(new Call());
    service.execute(new Call());
    service.execute(new Call());
    service.execute(new Call());
    service.execute(new Call());



    service.shutdown();

  }


}
