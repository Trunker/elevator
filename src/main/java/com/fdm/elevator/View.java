package com.fdm.elevator;

import java.util.Scanner;

public class View {
	private Scanner in;

	public View(Scanner in) {
		super();
		this.in = in;
	}

	public int[] getInitValue() {
		int numFloor = in.nextInt();
		int numElevator = in.nextInt();
		int capacity = in.nextInt();
		int[] initValue = { numFloor, numElevator, capacity };
		return initValue;
	}

	public int[] getRequest() {
		int pickupFloor = in.nextInt();
		int dropoffFloor = in.nextInt();
		int[] result = { pickupFloor, dropoffFloor };
		return result;
	}
}
