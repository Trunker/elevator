package com.fdm.elevator;

public class Building implements BuildingFactory {

	ElevatorScheduler eleSch;
	
	@Override
	public Building getBuilding(int arg) {
		// TODO Auto-generated method stub
		return null;
	}

	public Elevator assignElevator(Person person) {
		return eleSch.assignElevatorWithBuilding(person.getPickUpFloor(), person.getDropOffFloor(), this);		
	}
}
