package com.almundo.call.entities;

/** clase supervisor.
 * 
 * @author pablo.paparini. */
public class Supervisor extends Employee {

  /** Constructor.
   * 
   * @param name */
  public Supervisor(String name) {
    super(name);
  }

  public static final int PRIORITY = 2;

  @Override
  public int getPriority() {
    return PRIORITY;
  }



}
