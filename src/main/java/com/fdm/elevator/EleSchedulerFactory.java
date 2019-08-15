package com.fdm.elevator;

public interface EleSchedulerFactory {
	ElevatorScheduler getScheduler(int elevatorCount, int elevatorCapacity, int noOfFloors);
}
