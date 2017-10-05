package com.almundo.call.entities;

/** Clase padre empleado.
 * 
 * @author pablo.paparini */
public abstract class Employee implements Comparable<Employee> {

  private String name;

  /** Metodo que devuevle la prioridad de cada empleado.
   * 
   * @return */
  public abstract int getPriority();

  /** Constructor.
   * 
   * @param nombre */
  public Employee(String nombre) {
    this.name = nombre;
  }



  /** Metodo comparador.
   * 
   * @param other
   * @return */
  public int compareTo1(Employee other) {

    if (this.getPriority() < other.getPriority()) {
      return -1;
    } else if (this.getPriority() > other.getPriority()) {
      return 1;
    } else {
      return 0;
    }

  }

  /** @return */
  public String getName() {
    return name;
  }

}
