package org.springframework.samples.petclinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTests {

	Person testPerson;

	@BeforeEach
	void setup() throws FileNotFoundException {
		testPerson = new Person();
		testPerson.setFirstName(InputData.firstName);
		testPerson.setLastName(InputData.lastName);
	}

	@Test
	void testGetFirstName() {
		// TODO Zhanna
		assertEquals(testPerson.getFirstName(), InputData.firstName);

	}

	@Test
	void testSetFirstName() {
		// TODO Zhanna
		testPerson.setFirstName(InputData.firstName2);
		assertEquals(testPerson.getFirstName(), InputData.firstName2);
	}

	@Test
	void testSetFirstNameNull() {
		// TODO Zhanna
		testPerson.setFirstName(null);
		assertNull(testPerson.getFirstName());
	}

	@Test
	void testGetLastName() {
		// TODO Zhanna
		assertEquals(testPerson.getLastName(), InputData.lastName);
	}

	@Test
	void testSetLastName() {
		// TODO Zhanna
		testPerson.setLastName(InputData.lastName2);
		assertEquals(testPerson.getLastName(), InputData.lastName2);
	}

	@Test
	void testSetLastNameNull() {
		// TODO Zhanna
		testPerson.setLastName(null);
		assertNull(testPerson.getLastName());
	}

}
