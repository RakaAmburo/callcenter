package com.almundo.call.entities;

public class Operator extends Employee {

  public Operator(String name) {
    super(name);
  }

  public static final int LEVEL = 1;

  @Override
  public int getLevel() {
    return LEVEL;
  }

}