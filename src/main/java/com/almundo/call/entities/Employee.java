package com.almundo.call.entities;

public abstract class Employee implements Comparable<Employee> {

  public abstract int getLevel();


  public int compareTo(Employee other) {

    if (this.getLevel() < other.getLevel())
      return -1;
    else if (this.getLevel() > other.getLevel())
      return 1;
    else
      return 0;

  }

}
