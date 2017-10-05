package com.almundo.call.entities;

/** Clase Operador.
 * 
 * @author pablo.paparini */
public class Operator extends Employee {

  /** Constructor.
   * 
   * @param name */
  public Operator(String name) {
    super(name);
  }

  public static final int PRIORITY = 1;

  @Override
  public int getPriority() {
    return PRIORITY;
  }

}
