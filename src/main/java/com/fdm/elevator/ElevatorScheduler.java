package com.fdm.elevator;

public class ElevatorScheduler implements EleSchedulerFactory {

	Elevator[] elevators;

	@Override
	public ElevatorScheduler getScheduler(int elevatorCount, int maxCapacity, int noOfFloors) {
		ElevatorScheduler eleSch = new ElevatorScheduler();
		ElevatorFactory elevatorFacotry = new Elevator();
		for (int i = 0; i < elevatorCount; i++) {
			eleSch.elevators[i] = elevatorFacotry.getElevator(maxCapacity, 0, noOfFloors);
		}
		return eleSch;
	}

	public Elevator assignElevatorWithBuilding(int pick, int drop, Building building) {
		// TODO TestInBuilding
		int score = 0;
		int temp;
		int resultIndex = 0;

		try {
			for (int i = 0; i < elevators.length; i++) {
				temp = elevators[i].getScoreToAssignElevator(pick, drop, building);
				if (temp > score) {
					resultIndex = i;
				}
			}
			return elevators[resultIndex];
		} catch (Exception e) {
			return elevators[elevators.length - 1];
		}
	}

}
