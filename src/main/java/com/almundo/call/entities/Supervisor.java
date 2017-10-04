package com.almundo.call.entities;

public class Supervisor extends Employee {

  public Supervisor(String name) {
    super(name);
  }

  public static final int LEVEL = 2;

  @Override
  public int getLevel() {
    return LEVEL;
  }



}
