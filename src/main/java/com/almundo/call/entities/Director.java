package com.almundo.call.entities;

public class Director extends Employee {

  public Director(String name) {
    super(name);
  }

  public static final int LEVEL = 3;

  @Override
  public int getLevel() {
    return LEVEL;
  }

}
