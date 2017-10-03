package com.almundo.call.entities;

public class Director extends Employee {

  public static final int LEVEL = 2;

  @Override
  public int getLevel() {
    return LEVEL;
  }

}
