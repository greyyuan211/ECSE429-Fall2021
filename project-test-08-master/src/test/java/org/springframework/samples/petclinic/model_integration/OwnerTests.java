package org.springframework.samples.petclinic.model_integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.persistence.OwnerRepository;
import org.springframework.samples.petclinic.persistence.PetRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OwnerTests {

	@InjectMocks
	private Owner testOwner;

	@Mock
	Pet pet1;

	@Mock
	Pet pet2;

	@Mock
	Pet pet3;

	@Mock
	Pet pet;

	@Mock
	Pet duplicatePet;

	@Mock
	OwnerRepository ownerRepository;

	@Mock
	PetRepository petRepository;

	@BeforeEach
	void setup() {
		testOwner = new Owner();
		testOwner.setFirstName("John");
		testOwner.setLastName("Coltrane");
		testOwner.setAddress("52nd Street");
		testOwner.setCity("New York");
		testOwner.setTelephone("2125634567");
		testOwner.setId(1);

	}

	@Test
	void testGetPetsInternalIfPetsIsNull() {
		// TODO Ruixin Zhanna
		assertEquals(testOwner.getPetsInternal().size(), 0);
	}

	@Test
	void testGetPetsInternal() {
		// TODO Ruixin Zhanna
		Set<Pet> pets = new HashSet<>();
		pets.add(pet1);
		pets.add(pet2);
		pets.add(pet3);
		testOwner.setPetsInternal(pets);
		assertEquals(testOwner.getPetsInternal().size(), 3);
	}

	@Test
	void testGetPetsInternalNull() {
		// TODO Ruixin
		Set<Pet> result = new HashSet<Pet>();
		assertEquals(testOwner.getPetsInternal(), new HashSet<Pet>());
	}

	@Test
	void testSetPetsInternalValidPet() {
		// TODO Ruixin
		Set<Pet> pets = new HashSet<>();
		pets.add(pet1);
		pets.add(pet2);
		pets.add(pet3);
		testOwner.setPetsInternal(pets);
		assertEquals(testOwner.getPetsInternal(), pets);

	}

	@Test
	void testSetPetsInternalNull() {
		// TODO Ruixin
		testOwner.setPetsInternal(null);
		assertThat(testOwner.getPetsInternal().isEmpty());
	}

	@Test
	void testGetPets() {
		// TODO Ruixin
		Set<Pet> pets = new HashSet<>();
		when(pet1.getName()).thenReturn("suzy");
		when(pet2.getName()).thenReturn("archie");
		when(pet3.getName()).thenReturn("coco");
		pets.add(pet1);
		pets.add(pet2);
		pets.add(pet3);
		testOwner.setPetsInternal(pets);
		List<Pet> result = new ArrayList<>();
		result.add(pet2);
		result.add(pet3);
		result.add(pet1);
		assertEquals(testOwner.getPets(), result);
	}

	@Test
	void testAddPetValidPet() {
		// TODO Ruixin
		when(pet.isNew()).thenReturn(true);
		// no need to mock the setOwner method since it has nothing to do with Owner class
		doNothing().when(pet).setOwner(testOwner);
		testOwner.addPet(pet);
		assertEquals(testOwner.getPetsInternal().contains(pet), true);

	}

	@Test
	void testAddPetNull() {
		// TODO Zhanna
		// Exception gets thrown when addPet gets called which is what should happen.
		// A null pet should never be added.
		assertThrows(Exception.class, () -> testOwner.addPet(null));
	}

	@Test
	void testGetPetExistentName() {
		// TODO Zhanna
		Set<Pet> pets = new HashSet<>();
		when(pet1.getName()).thenReturn("suzy");
		pets.add(pet1);
		testOwner.setPetsInternal(pets);
		assertEquals(testOwner.getFirstName(), "John");
		assertEquals(testOwner.getPet("suzy").getName(), "suzy");
	}

	@Test
	void testGetPetNonExistentName() {
		// TODO Zhanna
		Set<Pet> pets = new HashSet<>();
		when(pet1.getName()).thenReturn("suzy");
		pets.add(pet1);
		testOwner.setPetsInternal(pets);
		assertEquals(testOwner.getFirstName(), "John");
		assertNull(testOwner.getPet("coco"));
	}

	@Test
	void testGetPetNull() {
		// TODO Zhanna
		assertEquals(testOwner.getFirstName(), "John");
		assertThrows(NullPointerException.class, () -> testOwner.getPet(null));
	}

	@Test
	void testGetPetOverloadedExistentName() {
		// TODO Zhanna
		Set<Pet> pets = new HashSet<>();
		when(pet1.getName()).thenReturn("suzy");
		pets.add(pet1);
		testOwner.setPetsInternal(pets);
		assertEquals(testOwner.getFirstName(), "John");
		assertEquals(testOwner.getPet("suzy", true).getName(), "suzy");
		assertEquals(testOwner.getPet("suzy", false).getName(), "suzy");
	}

	@Test
	void testGetPetOverloadedNonExistentName() {
		// TODO Zhanna
		Set<Pet> pets = new HashSet<>();
		when(pet1.getName()).thenReturn("suzy");
		pets.add(pet1);
		testOwner.setPetsInternal(pets);
		assertEquals(testOwner.getFirstName(), "John");
		assertNull(testOwner.getPet("coco", true));
		assertNull(testOwner.getPet("coco", false));
	}

	@Test
	void testGetPetOverloadedNull() {
		// TODO Zhanna
		assertEquals(testOwner.getFirstName(), "John");
		assertThrows(NullPointerException.class, () -> testOwner.getPet(null, true));
		assertThrows(NullPointerException.class, () -> testOwner.getPet(null, false));
	}

}
