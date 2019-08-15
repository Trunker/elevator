package com.fdm.elevator;

public class Path {

	/**
	 * nextTrue is the next floor number
	 */
	private boolean[][] stopFloors;
	private int iIndex = 0;
	private final int noOfFloorsMinus1;
	private boolean closed = true;
	private int[] destination = { -1, -1, -1, -1 };

	/**
	 * 
	 * @param noOfFloors
	 *            the number of Floors
	 */
	public Path(int noOfFloors) {
		this.noOfFloorsMinus1 = noOfFloors - 1;
		this.stopFloors = new boolean[4][noOfFloors];
		this.destination[1] = noOfFloors;
		this.destination[3] = noOfFloors;
	}

	public boolean isCurrent(int currentFloor) {
		return stopFloors[iIndex][currentFloor];
	}

	// 0 is good, 1 is normal, 2 is bad
	public int getPriority(int pick, int drop, int current) {
		if (isUp()) {
			if (pick < drop) {
				if (current < pick) {
					return 0;
				} else {
					return 2;
				}
			} else {
				return 1;
			}
		} else {
			if (pick > drop) {
				if (current > pick) {
					return 0;
				} else {
					return 2;
				}
			} else {
				return 1;
			}
		}
	}

	public synchronized void add(int pick, int drop, int current) {
		int i = getPriority(pick, drop, current);
		
		stopFloors[(getiIndex() + i) % 4][pick] = true;
		stopFloors[(getiIndex() + i) % 4][drop] = true;
		updateDestination(pick, drop, i);
		
		closed = false;
	}
	
	public void removeCurrentTry(int current) {
		stopFloors[getiIndex()][current] = false;
		if (current == destination[getiIndex()]) {
			resetDesination();
			setiIndex((getiIndex() + 1) % 4);
			if (destination[getiIndex()] == noOfFloorsMinus1 + 1 || destination[getiIndex()] == -1) {
				closed = true;
			}
		}
	}

	private void updateDestination(int pick, int drop, int i) {
		if (isUp()) {
			if (i == 0) {
				if (destination[(getiIndex() + i) % 4] < drop) {
					destination[(getiIndex() + i) % 4] = drop;
					stopFloors[(getiIndex() + i) % 4][drop] = true;
				}
			} else if (i == 1) {
				if (destination[(getiIndex() + i) % 4] > drop) {
					destination[(getiIndex() + i) % 4] = drop;
					stopFloors[(getiIndex() + i) % 4][drop] = true;
				}
				if (destination[(getiIndex() + i - 1) % 4] < pick) {
					destination[(getiIndex() + i - 1) % 4] = pick;
					stopFloors[(getiIndex() + i - 1) % 4][pick] = true;
				}
			} else if (i == 2) {
				if (destination[(getiIndex() + i) % 4] < drop) {
					destination[(getiIndex() + i) % 4] = drop;
					stopFloors[(getiIndex() + i) % 4][drop] = true;
				}
				if (destination[(getiIndex() + i - 1) % 4] > pick) {
					destination[(getiIndex() + i - 1) % 4] = pick;
					stopFloors[(getiIndex() + i - 1) % 4][pick] = true;
				}
			}
		} else {
			if (i == 0) {
				if (destination[(getiIndex() + i) % 4] > drop) {
					destination[(getiIndex() + i) % 4] = drop;
					stopFloors[(getiIndex() + i) % 4][drop] = true;
				}
			} else if (i == 1) {
				if (destination[(getiIndex() + i) % 4] < drop) {
					destination[(getiIndex() + i) % 4] = drop;
					stopFloors[(getiIndex() + i) % 4][drop] = true;
				}
				if (destination[(getiIndex() + i - 1) % 4] > pick) {
					destination[(getiIndex() + i - 1) % 4] = pick;
					stopFloors[(getiIndex() + i - 1) % 4][pick] = true;
				}
			} else if (i == 2) {
				if (destination[(getiIndex() + i) % 4] > drop) {
					destination[(getiIndex() + i) % 4] = drop;
					stopFloors[(getiIndex() + i) % 4][drop] = true;
				}
				if (destination[(getiIndex() + i - 1) % 4] < pick) {
					destination[(getiIndex() + i - 1) % 4] = pick;
					stopFloors[(getiIndex() + i - 1) % 4][pick] = true;
				}
			}
		}
	}

	public boolean[][] getStopFloors() {
		return stopFloors;
	}

	public void setStopFloors(boolean[][] stopFloors) {
		this.stopFloors = stopFloors;
	}

	public int[] getDestination() {
		return destination;
	}

	public void setDestination(int[] destination) {
		this.destination = destination;
	}

	public int getNoOfFloorsMinus1() {
		return noOfFloorsMinus1;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	private void resetDesination() {
		if (isUp()) {
			destination[getiIndex()] = -1;
		} else {
			destination[getiIndex()] = noOfFloorsMinus1 + 1;
		}
	}

	public boolean isUp() {
		return (iIndex % 2 == 0);
	}


	public boolean getStopAtNextFloor(int current) {
		return false;
	}

	public boolean get(int i, int j) {
		return stopFloors[i][j];
	}

	public int getiIndex() {
		return iIndex % 4;
	}

	public void setiIndex(int iIndex) {
		this.iIndex = iIndex;
	}

	public boolean getClosed() {
		return closed;
	}

}
