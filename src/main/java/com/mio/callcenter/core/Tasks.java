package com.mio.callcenter.core;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.mio.callcenter.entities.Call;
import com.mio.callcenter.util.TimeOut;

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
				TimeOut to = new TimeOut(5000, 10000);
				// Taking call from log or web output
				System.out.println("Taking call from " + call.getCustomer().getName() + " => "
						+ call.getAttendant().getName() + " (" + call.getAttendant().getClass().getSimpleName() + ")");
				int time = to.setRandomTimeOut();
				System.out.println("Call ended for " + call.getCustomer().getName() + ", duration: "
						+ TimeUnit.MILLISECONDS.toSeconds(time) + " Seconds. " + "Releasing " + call.getAttendant().getName());
				// Ending call log or web output
			} catch (Exception e) {
				e.printStackTrace();// log
			} finally {
				// return the employe to the attenderPool
				cc.returnAttender(call.getAttendant());
			}

		};
	}

	public Runnable dispatchCalls(Dispatcher disp) {
		return () -> {
			try {
				disp.dispatchCall();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
	}

}
