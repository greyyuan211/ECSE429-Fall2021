package org.springframework.samples.petclinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BaseEntityTests {

	Owner testOwner;

	Pet testPet;

	Visit testVisit;

	PetType testPetType;

	Person testPerson;

	Person testPersonIsNew;

	Person testGetInvalid;

	@BeforeEach
	void setup() {
		testPerson = new Person();
		testOwner = new Owner();
		testPet = new Pet();
		testVisit = new Visit();
		testPetType = new PetType();
		testPersonIsNew = new Person();
		testGetInvalid = new Person();
		testPerson.setId(2);
		testOwner.setId(3);
		testPet.setId(4);
		testVisit.setId(5);
		testPetType.setId(6);
	}

	@Test
	void testPersonGetId() {
		// TODO Ruixin
		assertEquals(2, testPerson.getId());
	}

	@Test
	void testOwnerGetId() {
		// TODO Ruixin
		assertEquals(3, testOwner.getId());
	}

	@Test
	void testPetGetId() {
		// TODO Ruixin
		assertEquals(4, testPet.getId());
	}

	@Test
	void testVisitGetId() {
		// TODO Ruixin
		assertEquals(5, testVisit.getId());
	}

	@Test
	void testPetTypeGetId() {
		// TODO Ruixin
		assertEquals(6, testPetType.getId());
	}

	@Test
	void testPersonSetId() {
		// TODO Ruixin
		testPerson.setId(3);
		assertEquals(3, testPerson.getId());
	}

	@Test
	void testOwnerSetId() {
		// TODO Ruixin
		testOwner.setId(4);
		assertEquals(4, testOwner.getId());
	}

	@Test
	void testPetSetId() {
		// TODO Ruixin
		testPet.setId(5);
		assertEquals(5, testPet.getId());
	}

	@Test
	void testVisitSetId() {
		// TODO Ruixin
		testVisit.setId(6);
		assertEquals(6, testVisit.getId());
	}

	@Test
	void testPetTypeSetId() {
		// TODO Ruixin
		testPetType.setId(7);
		assertEquals(7, testPetType.getId());
	}

	@Test
	void testGetInvalidId() {
		// TODO Ruixin
		assertNull(testGetInvalid.getId());
	}

	@Test
	void testIsNew() {
		// TODO Ruixin
		Person testPersonIsNew = new Person();
		assertNull(testPersonIsNew.getId());
	}

}
