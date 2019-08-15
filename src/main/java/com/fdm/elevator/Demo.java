package com.fdm.elevator;

import java.util.ArrayList;

public class Demo {

	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> a = new ArrayList<Integer>();
		ArrayList<Integer> b = new ArrayList<Integer>();
//		for (int i = 1; i < 10; i++) {
//			a.add(i);
//		}
//		for (int i = 9; i > 1; i--) {
//			b.add(i);
//		}
		a.add(2);
		a.add(4);
		b.add(2);
		paths.add(a);
		paths.add(b);
		Elevator e = new Elevator();
		e.setPaths(paths);
		Thread t = new Thread(e);
		t.start();
	}

}
