package com.mio.callcenter.entities;

public class Call {

	private Employee attendant;
	private Customer customer;
	
	public Call(Customer cust) {
		this.customer = cust;
	}

	public Employee getAttendant() {
		return attendant;
	}

	public void setAttendant(Employee attendant) {
		this.attendant = attendant;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
