package com.almundo.call.util;

import java.util.Comparator;

import com.almundo.call.entities.Employee;

public class LevelComparator implements Comparator<Employee> {

  public int compare(Employee e1, Employee e2) {

    if (e1.getLevel() < e2.getLevel())
      return -1;
    else if (e1.getLevel() > e2.getLevel())
      return 1;
    else
      return 0;

  }

}
