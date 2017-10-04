package com.almundo.call.entities;

public abstract class Employee implements Comparable<Employee> {

  private String name;

  public abstract int getLevel();

  public Employee(String name) {
    this.name = name;
  }


  public int compareTo(Employee other) {

    if (this.getLevel() < other.getLevel())
      return -1;
    else if (this.getLevel() > other.getLevel())
      return 1;
    else
      return 0;

  }

  public String getName() {
    return name;
  }

}
