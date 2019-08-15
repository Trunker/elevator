package com.fdm.elevator;

public class Person {

	private int dropOffFloor;
	private int pickupFloor;
	
	public Person(int dropOffFloor, int pickUpFloor) {
		this.dropOffFloor = dropOffFloor;
		this.pickupFloor = pickUpFloor;
	}
	public int getDropOffFloor() {
		// TODO Auto-generated method stub
		return this.dropOffFloor;
	}

	public int getPickUpFloor() {
		// TODO Auto-generated method stub
		return this.pickupFloor;
	}

}
