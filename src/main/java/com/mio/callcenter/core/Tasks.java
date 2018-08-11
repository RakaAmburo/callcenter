package com.mio.callcenter.core;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.mio.callcenter.entities.Call;




public class Tasks {

	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
			public void run() {
				// task to run goes here
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Hello !!");
			}
		};
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
	}

	public Runnable callProcessor(Call call, CallCenter cc) {
		return () -> {
			try {
				// Taking call from log or web output
				System.out.println("Taking call from " + call.getCustomer().getName() + " => "
						+ call.getAttendant().getName() + " (" + call.getAttendant().getClass().getSimpleName() + ")");
				Thread.sleep(100);
				System.out.println("Call ended for " + call.getCustomer().getName() + ", duration: "
						+ TimeUnit.MILLISECONDS.toSeconds(100) + " Seconds.");
				// Ending call log or web output
			} catch (InterruptedException e) {
				e.printStackTrace();// log
			} finally {
				// return the employe to the attenderPool
			   cc.returnAtender(call.getAttendant());
			}

		};
	}

	public Runnable dispatchCalls(String employee, String customer, String attenderPool) {
		return () -> {
			try {
				while (true) {
					throw new InterruptedException();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();// log
			}
		};
	}

}
