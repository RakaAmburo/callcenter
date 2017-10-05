package com.almundo.call.entities;

/** Clase Director.
 * 
 * @author pablo.paparini */
public class Director extends Employee {

  /** Constructor.
   * 
   * @param name */
  public Director(String name) {
    super(name);
  }

  public static final int PRIORITY = 3;

  @Override
  public int getPriority() {
    return PRIORITY;
  }

}
