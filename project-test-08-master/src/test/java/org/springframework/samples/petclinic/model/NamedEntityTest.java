package org.springframework.samples.petclinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NamedEntityTest {

	Pet testPet;

	Specialty testSpecialty;

	@BeforeEach
	void setup() throws FileNotFoundException {
		testPet = new Pet();
		testSpecialty = new Specialty();
		testPet.setName("Max");
		testSpecialty.setName("Dermatology");

	}

	@Test
	void testPetSetName() {
		// TODO Ruixin
		testPet.setName("Oliver");
		assertEquals("Oliver", testPet.getName());
	}

	@Test
	void testSpecialtySetName() {
		// TODO Ruixin
		testSpecialty.setName("Anesthesia");
		assertEquals("Anesthesia", testSpecialty.getName());
	}

	@Test
	void testPetGetName() {
		// TODO Ruixin
		assertEquals("Max", testPet.getName());
	}

	@Test
	void testSpecialtyGetName() {
		// TODO Ruixin
		assertEquals("Dermatology", testSpecialty.getName());
	}

}
