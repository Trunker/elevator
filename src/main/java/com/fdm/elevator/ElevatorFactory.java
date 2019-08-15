package com.fdm.elevator;

public interface ElevatorFactory {

	Elevator getElevator(int maxCapacity, int currentFloor, int noOfFloors);
	void moveUp();
	void moveDown();
}
