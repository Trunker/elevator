package com.fdm.elevator;

import static org.junit.Assert.*;

import org.junit.Test;

public class PathTest {

	@Test
	public void test_constructor() {
		Path path = new Path(4);
		assertFalse(path.get(3, 2));
	}

	@Test
	public void test_updateDestination() {
		Path path = new Path(10);

		assertEquals(path.getDestination()[0], -1);

		path.add(3, 7, 2); // 37
		assertEquals(7, path.getDestination()[0]);

		path.add(2, 7, 3); // 37 _ 27
		assertEquals(path.getDestination()[2], 7);
		assertEquals(path.getDestination()[0], 7);
		assertEquals(path.getDestination()[1], 2);
		assertTrue(path.get(1, 2));

		path.add(8, 2, 3); // add 37 82 27
		assertEquals(8, path.getDestination()[0]);
		assertEquals(2, path.getDestination()[1]);
		assertEquals(7, path.getDestination()[2]);
	}

	@Test
	public void test_updateDestination_whenIndexEquals2() {
		Path path = new Path(10);
		path.setiIndex(2);
		assertEquals(path.getDestination()[0], -1);

		path.add(3, 7, 2); // 37
		assertEquals(7, path.getDestination()[2]);

		path.add(2, 7, 3); // 37 _ 27
		assertEquals(path.getDestination()[0], 7);
		assertEquals(path.getDestination()[2], 7);
		assertEquals(path.getDestination()[3], 2);
		assertTrue(path.get(3, 2));

		path.add(8, 2, 3); // add 37 82 27
		assertEquals(8, path.getDestination()[2]);
		assertEquals(2, path.getDestination()[3]);
		assertEquals(7, path.getDestination()[0]);
	}

	@Test
	public void test_updateDestination_whenIndexEquals3() {
		Path path = new Path(10);
		path.setiIndex(3);
		assertEquals(path.getDestination()[3], 10);

		path.add(7, 3, 8); //
		assertEquals(3, path.getDestination()[3]);

		path.add(9, 7, 8); // 73 _ 97 
		assertEquals(3, path.getDestination()[3]);
		assertEquals(9, path.getDestination()[0]);
		assertEquals(7, path.getDestination()[1]);
		assertTrue(path.get(0, 9));

		path.add(2, 8, 3); // add 73 28 97
		assertEquals(2, path.getDestination()[3]);
		assertEquals(9, path.getDestination()[0]);
		assertEquals(7, path.getDestination()[1]);
		assertTrue(path.get(3, 2));

	}
	
	@Test
	public void test_add_pickdropcurrent() {
		Path path = new Path(10);
		path.add(3, 4, 3);
		assertTrue(path.get(2, 3));

		path = new Path(10);
		path.setiIndex(2);
		path.add(5, 4, 3);
		assertTrue(path.get(3, 5));
		assertTrue(path.get(3, 4));

		path = new Path(10);
		path.setiIndex(3);
		path.add(3, 4, 3);
		assertFalse(path.get(2, 3));
		assertTrue(path.get(0, 3));
		assertTrue(path.get(0, 4));

		path = new Path(5);
		path.add(3, 4, 2);
		assertTrue(path.get(0, 3));
		assertTrue(path.get(0, 4));

		path = new Path(10);
		path.setiIndex(3);
		path.add(3, 2, 4);
		assertTrue(path.get(3, 3));
		assertTrue(path.get(3, 2));

		path = new Path(10);
		path.setiIndex(3);
		path.add(5, 3, 4);
		assertTrue(path.get(1, 3));
		assertTrue(path.get(1, 5));
	}

	@Test
	public void test_removeCurrent() {
		Path path = new Path(10);
		path.setiIndex(0);
		path.add(8, 9, 1);
		assertFalse(path.getClosed());
		path.removeCurrentTry(8);

		assertFalse(path.getClosed());
		assertEquals(path.getiIndex(), 0);
		assertEquals(path.getDestination()[0], 9);
		
		
		path.removeCurrentTry(9);
		assertEquals(path.getiIndex(), 1);
		assertEquals(path.getDestination()[0], -1);
		assertTrue(path.getClosed());
		
		path = new Path(10);
		path.setiIndex(1);
		path.add(3, 0, 5);
		path.removeCurrentTry(3);
		assertEquals(path.getiIndex(), 1);
		path.removeCurrentTry(0);
		assertEquals(path.getiIndex(), 2);
	}
	//
	// @Test
	// public void test_removeCurrentFloor() {
	// Path path = new Path(10);
	// path.setiIndex(0);
	// assertTrue(path.getClosed());
	// path.add(8, 9, 1);
	// assertFalse(path.getClosed());
	// path.removeCurrentFloor(8);
	// assertTrue(path.getClosed());
	// assertEquals(path.getiIndex(), 0);
	// path.removeCurrentTry(9);
	// assertFalse(path.getClosed());
	// assertEquals(path.getiIndex(), 2);
	//
	// path = new Path(10);
	// path.setiIndex(1);
	// path.add(3, 0, 5);
	// path.removeCurrentTry(3);
	// assertEquals(path.getiIndex(), 1);
	// path.removeCurrentTry(0);
	// assertEquals(path.getiIndex(), 2);
	// }

}
