package com.mio.callcenter.core;


import com.mio.callcenter.entities.Call;
import com.mio.callcenter.entities.Employee;

public class Dispatcher {
	
	private CallCenter callCenter;
	private int callsCount = 0;
	
	public int getCallsCount() {
		return callsCount;
	}

	public Dispatcher(CallCenter cc) {
		this.callCenter = cc;
	}
	
	public void dispatchCall() throws InterruptedException {
		Call call = callCenter.takeNextCall();
		Employee employee = callCenter.takeFreeAttender();
		call.setAttendant(employee);
		callCenter.processCall(call);
		callsCount++;
	}
	

}
