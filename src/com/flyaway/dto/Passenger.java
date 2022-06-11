package com.flyaway.dto;

public class Passenger {
     private String passangerName;
     private String DoB;
     private String email;
     private String phone;
     private String address;
     //private int seatNo;
	
     public Passenger() {
		super();
	}
	
	
	


public Passenger(String passangerName, String doB, String email, String phone, String address) {
		super();
		this.passangerName = passangerName;
		this.DoB = doB;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}


	public String getPassangerName() {
		return passangerName;
	}
	public void setPassangerName(String passangerName) {
		this.passangerName = passangerName;
	}
	public String getDoB() {
		return DoB;
	}
	public void setDoB(String doB) {
		DoB = doB;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "Passenger [passangerName=" + passangerName + ", DoB=" + DoB + ", email=" + email + ", phone=" + phone
				+ ", address=" + address +  "]";
	}
	
	
	
 
}
