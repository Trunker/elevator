package com.fdm.elevator;

import java.util.ArrayList;

public class Elevator implements ElevatorFactory, ElevatorBehaviour, Runnable {

	private ArrayList<Person> personInElevator;
	private int currentFloor;
	private ArrayList<ArrayList<Integer>> paths;
	private Path path;
	private Building b;

	public ArrayList<ArrayList<Integer>> getPaths() {
		return paths;
	}

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public void setCurrentCapacity(int currentCapacity) {
		this.currentCapacity = currentCapacity;
	}

	private int maxCapacity;
	private int currentCapacity;

	@Override
	public Elevator getElevator(int maxCapacity, int currentFloor, int noOfFloors) {
		Elevator elevator = new Elevator();
		elevator.maxCapacity = maxCapacity;
		elevator.currentFloor = currentFloor;
		elevator.path = new Path(noOfFloors);
		return elevator;
	}

	public void offLoadPerson(int floorNumber) {
		if (personInElevator.size() > 0) {
			for (int i = 0; i < getPersonInElevator().size(); i++) {
				if (personInElevator.get(i).getDropOffFloor() == floorNumber) {
					personInElevator.remove(i);
//					this.currentCapacity--;
				}
			}
		}
	}

	public ArrayList<Person> getPersonInElevator() {
		return this.personInElevator;
	}

	public void setPersonInElevator(ArrayList<Person> personInElevator) {
		this.personInElevator = personInElevator;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public Path getPath() {
		return path;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public int getCurrentCapacity() {
		return currentCapacity;
	}

	public void loadPerson() {
		while(b.hasWaitingPerson(this)) {
			personInElevator.add(b.removePerson(this.currentFloor));			
//			while (this.currentCapacity != this.maxCapacity) {				
//				this.currentCapacity++;
			}
		}
	

	public int getScoreToAssignElevator(int pick, int drop, Building building) {
		return path.getPriority(pick, drop, currentFloor);
	}

	public Elevator() {
		super();
		this.currentFloor = 0;
	}

	public void setPaths(ArrayList<ArrayList<Integer>> paths) {
		this.paths = paths;
	}

	@Override
	public void moveUp() {
		while (!paths.isEmpty()) {
			while (!paths.get(0).isEmpty()) {
				if (this.currentFloor == paths.get(0).get(0)) {
					offLoadPerson(this.currentFloor);
					loadPerson();
					System.out.println("Up Do sth" + this.currentFloor);// we have arrived at particular floor, load																		// people
																		// or drop people off
					paths.get(0).remove(0);
				} else {
					this.currentFloor++;
				}
			}
			paths.remove(0);
			moveDown();
		}
	}

	@Override
	public void moveDown() {
		// TODO Auto-generated method stub
		while (!paths.isEmpty()) {
			while (!paths.get(0).isEmpty()) {
				if (this.currentFloor == paths.get(0).get(0)) {
					offLoadPerson(this.currentFloor);
					loadPerson();
					System.out.println("Down Do sth" + this.currentFloor);// we have arrived at particular floor, load																			// people or drop people off
					paths.get(0).remove(0);
				} else {
					this.currentFloor--;
				}
			}
			paths.remove(0);
			moveUp();
		}
	}

	@Override
	public void run() {
		while (true) {
			while (!paths.isEmpty()) {
				if (this.currentFloor < paths.get(0).get(0)) {
					moveUp();
				} else if (this.currentFloor > paths.get(0).get(0)) {
					moveDown();
				}
			}
			synchronized (paths) {
				while (paths.isEmpty()) {
					try {
						System.out.println("Waiting");
						paths.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

}
