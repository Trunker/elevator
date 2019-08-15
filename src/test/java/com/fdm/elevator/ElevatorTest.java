package com.fdm.elevator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ElevatorTest {

	@Test
	public void test_getElevator() {
		// Arrange
		ElevatorFactory elevatorFactory = new Elevator();
		int maxCapacity = 5;
		int currentFloor = 3;
		int noOfFloors = 8;
		// Act
		Elevator elevator1 = elevatorFactory.getElevator(maxCapacity, currentFloor, noOfFloors);
		// Assert
		assertEquals(elevator1.getMaxCapacity(), 5);
		assertEquals(elevator1.getCurrentFloor(), 3);
	}
	
	@Test
	public void test_offLoadOnePerson_whenElevatorIsStoppingAtFloor3AndNoPeopleInElevator_returnPersonInElevatorIs0() {
		// Arrange
		Elevator elevator = new Elevator();
		ArrayList<Person> persons = new ArrayList<Person>();
		elevator.setPersonInElevator(persons);
		// Act
		elevator.offLoadPerson(3);
		// Assert
		assertEquals(elevator.getPersonInElevator().size(), 0);
	}

	@Test
	public void test_offLoadOnePerson_whenElevatorIsStoppingAtFloor3AndOnePersonIsDroppingAtFloor3_returnPersonInElevatorIs0() {
		// Arrange
		Elevator elevator = new Elevator();
		Person person = mock(Person.class);
		ArrayList<Person> persons = new ArrayList<Person>();
		persons.add(person);
		elevator.setPersonInElevator(persons);
		when(person.getDropOffFloor()).thenReturn(3);
		// Act
		elevator.offLoadPerson(3);
		// Assert
		assertEquals(elevator.getPersonInElevator().size(), 0);
		verify(person, times(1)).getDropOffFloor();
	}

	@Test
	public void test_offLoadOnePerson_whenElevatorIsStoppingAtFloor3AndTwoPersonIsDroppingAtFloor3OnePersonAtFloor2_returnPersonInElevatorIs1() {
		// Arrange
		Elevator elevator = new Elevator();
		Person person1 = mock(Person.class);
		Person person2 = mock(Person.class);
		Person person3 = mock(Person.class);
		ArrayList<Person> persons = new ArrayList<Person>();
		persons.add(person1);
		persons.add(person2);
		persons.add(person3);
		elevator.setPersonInElevator(persons);
		when(person1.getDropOffFloor()).thenReturn(3);
		when(person2.getDropOffFloor()).thenReturn(2);
		when(person3.getDropOffFloor()).thenReturn(3);
		// Act
		elevator.offLoadPerson(3);
		// Assert
		assertEquals(elevator.getPersonInElevator().size(), 1);
		verify(person1, times(1)).getDropOffFloor();
	}

	@Test
	public void test_loadPerson_whenOnePersonAdded_itIsAdded() {
		// Arrange
		Person person = mock(Person.class);
		Elevator elevator = new Elevator();
		Person person1 = mock(Person.class);
		Person person2 = mock(Person.class);
		Person person3 = mock(Person.class);
		ArrayList<Person> persons = new ArrayList<Person>();
		persons.add(person1);
		persons.add(person2);
		persons.add(person3);
		elevator.setPersonInElevator(persons);
		// Act
		elevator.loadPerson(person);
		// Act
		assertTrue(elevator.getPersonInElevator().contains(person));
	}

}