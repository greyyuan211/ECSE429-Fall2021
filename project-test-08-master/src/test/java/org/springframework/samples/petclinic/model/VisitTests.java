package org.springframework.samples.petclinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class VisitTests {

	Visit testVisit;

	@BeforeEach
	void setup() throws FileNotFoundException {
		testVisit = new Visit();
		// set date
		testVisit.setDate(InputData.validDate);
		// set description
		testVisit.setDescription(InputData.validDescription);
		// set pet id
		testVisit.setPetId(InputData.petId1);
	}

	@Test
	void testSetDateValid() {
		// TODO Linwei
		testVisit.setDate(InputData.validDate2);
		assertEquals(testVisit.getDate(), InputData.validDate2);
	}

	@Test
	void testSetDateNull() {
		// TODO Linwei
		testVisit.setDate(null);
		assertEquals(testVisit.getDate(), null);
	}

	@Test
	void testGetDescription() {
		// TODO Linwei
		assertEquals(testVisit.getDescription(), InputData.validDescription);
	}

	@Test
	void testSetDescriptionValid() {
		// TODO Linwei
		testVisit.setDescription(InputData.validDescription2);
		assertEquals(testVisit.getDescription(), InputData.validDescription2);
	}

	@Test
	void testSetDescriptionNull() {
		// TODO Linwei
		testVisit.setDescription(null);
		assertNull(testVisit.getDescription());
	}

	@Test
	void testGetPetID() {
		// TODO Linwei
		assertEquals(testVisit.getPetId(), InputData.petId1);
	}

	@Test
	void testSetPetID() {
		// TODO Linwei
		testVisit.setPetId(InputData.petId2);
		assertEquals(testVisit.getPetId(), InputData.petId2);
	}

}
