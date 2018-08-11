package com.mio.callcenter.util;

import java.util.Random;
import java.util.stream.IntStream;

public class TimeOut {

  // probar con numeros grandes para no hacer trabajar al director
  private static final int LOW = 200;
  private static final int HIGHT = 1000;

  public static void setRandomTimeOut() {

    Random r = new Random();

    int time = r.nextInt(HIGHT - LOW) + LOW;

    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
  
  public static void setRandomTimeOut(int times) {
	  
	  IntStream.rangeClosed(1, times).forEach(a -> setRandomTimeOut());
	  
  }

}
