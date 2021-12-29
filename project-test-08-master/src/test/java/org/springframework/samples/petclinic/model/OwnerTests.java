package org.springframework.samples.petclinic.model;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OwnerTests {

	Owner testOwner;

	@BeforeEach
	void setup() throws FileNotFoundException {
		testOwner = new Owner();
		testOwner.setAddress(InputData.validAddress);
		testOwner.setCity(InputData.validCity);
		testOwner.setTelephone(InputData.validTelephone);
	}

	@Test
	void testGetAddress() {
		// TODO Yacine
		assertEquals(testOwner.getAddress(), InputData.validAddress);
	}

	@Test
	void testSetAddressValidAddress() {
		// TODO Yacine
		testOwner.setAddress(InputData.validAddress2);
		assertEquals(testOwner.getAddress(), InputData.validAddress2);
	}

	@Test
	void testSetAddressNull() {
		// TODO Yacine
		testOwner.setAddress(null);
		assertEquals(testOwner.getAddress(), null);
	}

	@Test
	void testGetCity() {
		// TODO Yacine
		assertEquals(testOwner.getCity(), InputData.validCity);
	}

	@Test
	void testSetCityNull() {
		// TODO Yacine
		testOwner.setCity(null);
		assertEquals(testOwner.getCity(), null);
	}

	@Test
	void testSetCity() {
		// TODO Yacine
		testOwner.setCity(InputData.validCity2);
		assertEquals(testOwner.getCity(), InputData.validCity2);

	}

	@Test
	void testGetTelephone() {
		// TODO Mohamed
		assertEquals(testOwner.getTelephone(), InputData.validTelephone);
	}

	@Test
	void testSetValidTelephone() {
		// TODO Mohamed
		testOwner.setTelephone(InputData.validTelephone2);
		assertEquals(testOwner.getTelephone(), InputData.validTelephone2);
	}

	@Test
	void testSetTelephoneNull() {
		// TODO Mohamed
		testOwner.setTelephone(null);
		assertEquals(testOwner.getTelephone(), null);
	}

	@Test
	void testGetPetsInternal() {
		Set<Pet> pets = new HashSet<Pet>();
		Pet taya = new Pet();
		Pet vasia = new Pet();
		PetType type = new PetType();
		type.setName(InputData.petType);
		PetType type2 = new PetType();
		type.setName(InputData.petType2);

		taya.setName("Taya");
		taya.setBirthDate(InputData.validDate);
		taya.setOwner(testOwner);
		taya.setType(type);

		vasia.setName("Vasia");
		vasia.setBirthDate(InputData.validDate);
		vasia.setOwner(testOwner);
		vasia.setType(type2);

		pets.add(taya);
		pets.add(vasia);

		testOwner.setPetsInternal(pets);

		assertEquals(testOwner.getPetsInternal().size(), 2);
	}

	@Test
	void testGetPetsInternalPetListIsNull() {
		Set<Pet> pets = null;
		testOwner.setPetsInternal(pets);

		assertEquals(testOwner.getPetsInternal().size(), 0);
	}

	@Test
	void testGetPets() {
		Set<Pet> pets = new HashSet<Pet>();
		Pet taya = new Pet();
		Pet vasia = new Pet();
		PetType type = new PetType();
		type.setName(InputData.petType);
		PetType type2 = new PetType();
		type.setName(InputData.petType2);

		taya.setName("Taya");
		taya.setBirthDate(InputData.validDate);
		taya.setOwner(testOwner);
		taya.setType(type);

		vasia.setName("Vasia");
		vasia.setBirthDate(InputData.validDate);
		vasia.setOwner(testOwner);
		vasia.setType(type2);

		pets.add(taya);
		pets.add(vasia);

		testOwner.setPetsInternal(pets);

		assertEquals(testOwner.getPets().size(), 2);
	}

	@Test
	void testGetPetNonExistent() {
		Set<Pet> pets = new HashSet<Pet>();
		Pet taya = new Pet();
		Pet vasia = new Pet();
		PetType type = new PetType();
		type.setName(InputData.petType);
		PetType type2 = new PetType();
		type.setName(InputData.petType2);

		taya.setName("Taya");
		taya.setBirthDate(InputData.validDate);
		taya.setOwner(testOwner);
		taya.setType(type);

		vasia.setName("Vasia");
		vasia.setBirthDate(InputData.validDate);
		vasia.setOwner(testOwner);
		vasia.setType(type2);

		pets.add(taya);
		pets.add(vasia);

		testOwner.setPetsInternal(pets);

		assertNull(testOwner.getPet("Bobby", true));
	}

	@Test
	void testAddPet() {
		Set<Pet> pets = new HashSet<Pet>();
		Pet taya = new Pet();
		PetType type = new PetType();
		type.setName(InputData.petType);

		taya.setName("Taya");
		taya.setBirthDate(InputData.validDate);
		taya.setOwner(testOwner);

		testOwner.addPet(taya);

		assertNotNull(testOwner.getPet("Taya"));
		assertEquals(testOwner.getPet("Taya").getBirthDate().toString(), InputData.validDate.toString());
	}

	@Test
	void testToString() {
		assertNotNull(testOwner.toString());
	}

}
