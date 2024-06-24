package com.flight_reservation_app.entity;

public class ReservationUpdateRequest {
	
	private long id;
	private int numberOfBags;
	private boolean checkedIn;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNumberOfBags() {
		return numberOfBags;
	}
	public void setNumberOfBags(int numberOfBags) {
		this.numberOfBags = numberOfBags;
	}
	public boolean isCheckedIn() {
		return checkedIn;
	}
	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

}
