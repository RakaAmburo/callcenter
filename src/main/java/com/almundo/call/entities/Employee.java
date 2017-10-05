package com.almundo.call.entities;

/** Clase padre emleado.
 * 
 * @author pablo.paparini */
public abstract class Employee implements Comparable<Employee> {

  private String name;

  public abstract int getPriority();

  /** Constructor
   * 
   * @param name */
  public Employee(String name) {
    this.name = name;
  }


  public int compareTo(Employee other) {

    if (this.getPriority() < other.getPriority()) {
      return -1;
    } else if (this.getPriority() > other.getPriority()) {
      return 1;
    } else {
      return 0;
    }

  }

  public String getName() {
    return name;
  }

}
