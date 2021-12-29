
package org.springframework.samples.petclinic.model;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PetTests {

	Pet testPet;

	PetType petType;

	Owner owner;

	Visit visit;

	Visit visit2;

	Set<Visit> visits;

	@BeforeEach
	void setUp() throws FileNotFoundException {
		testPet = new Pet();
		petType = new PetType();
		owner = new Owner();
		visit = new Visit();
		visit2 = new Visit();
		visits = new HashSet<Visit>();

		petType.setName(InputData.petType);
		owner.setFirstName(InputData.firstName);
		owner.setLastName(InputData.lastName);
		visit.setDate(InputData.validDate);
		visit2.setDate(InputData.validDate2);
		visits.add(visit);
		visits.add(visit2);

		testPet.setBirthDate(InputData.validDate);
		testPet.setName(InputData.firstName);
		testPet.setId(InputData.petId1);
		testPet.setType(petType);
		testPet.setOwner(owner);
		testPet.setVisitsInternal(visits);

	}

	@Test
	void testGetBirthDate() {

		// TODO Mohamed
		assertEquals(testPet.getBirthDate(), InputData.validDate);
	}

	@Test
	void testSetBirthdayValidDate() {

		// TODO Mohamed
		testPet.setBirthDate(InputData.validDate2);
		assertNotNull(testPet);
		assertEquals(testPet.getBirthDate(), InputData.validDate2);
	}

	@Test
	void testSetBirthdayInvalidDate() {
		assertThrows(Exception.class,
				() -> LocalDate.of(InputData.invalidYear, InputData.invalidMonth, InputData.invalidDay));
		assertThrows(Exception.class, () -> testPet
				.setBirthDate(LocalDate.of(InputData.invalidYear, InputData.invalidMonth, InputData.invalidDay)));
	}

	@Test
	void testSetBirthdayNull() {
		// TODO Linwei
		testPet.setBirthDate(null);
		assertNull(testPet.getBirthDate());
	}

	@Test
	void testGetPetType() {
		assertEquals(testPet.getType().toString(), "dog");
	}

	@Test
	void testGetPetOwner() {
		assertNotNull(testPet.getOwner().toString());
		assertEquals(testPet.getOwner().getFirstName(), "Frida");
		assertEquals(testPet.getOwner().getLastName(), "Kahlo");
	}

	@Test
	void testGetVisitsInternal() {
		assertEquals(testPet.getVisitsInternal().size(), 2);
	}

	@Test
	void testGetVisits() {
		assertEquals(testPet.getVisits().size(), 2);
	}

	@Test
	void testGetVisitsInternalNullVisits() {
		Pet nullVisitPet = new Pet();
		assertEquals(nullVisitPet.getVisitsInternal(), new HashSet<>());
	}

	@Test
	void testAddVisit() {
		Visit newVisit = new Visit();
		newVisit.setDate(InputData.validDate3);

		testPet.addVisit(newVisit);
		visits.add(newVisit);
		assertEquals(testPet.getVisits().get(0).getDate().toString(), InputData.validDate3.toString());
	}

}
