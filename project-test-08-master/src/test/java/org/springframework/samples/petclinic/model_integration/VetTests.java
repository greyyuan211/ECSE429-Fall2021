package org.springframework.samples.petclinic.model_integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.*;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VetTests {

	@InjectMocks
	private Vet testVet;

	@Mock
	Specialty spc1;

	@Mock
	Specialty spc2;

	@Mock
	Specialty spc3;

	@Mock
	Specialty spc;

	@Mock
	Specialty duplicateSpc;

	@BeforeEach
	void setup() {
		testVet = new Vet();
		testVet.setFirstName("John");
		testVet.setLastName("Coltrane");

	}

	// source code slightly changed to facilitate the integration testing
	// protected -> public
	@Test
	void testSetSpecialtiesInternal() {
		// TODO Filip
		Set<Specialty> spcs = new HashSet<>();
		spcs.add(spc1);
		spcs.add(spc2);
		spcs.add(spc3);
		testVet.setSpecialtiesInternal(spcs);
		assertEquals(spcs, testVet.getSpecialtiesInternal());
	}

	@Test
	void testGetSpecialties() {
		// TODO Filip
		Set<Specialty> spcs = new HashSet<>();
		spcs.add(spc1);
		spcs.add(spc2);
		spcs.add(spc3);
		testVet.setSpecialtiesInternal(spcs);
		assertEquals(3, testVet.getSpecialtiesInternal().size());
	}

	@Test
	void testGetNumberOfSpecialties() {
		// TODO Filip
		Set<Specialty> spcs = new HashSet<>();
		// when(spc1.getName()).thenReturn("radiology");
		// when(spc2.getName()).thenReturn("surgery");
		spcs.add(spc1);
		spcs.add(spc2);
		testVet.setSpecialtiesInternal(spcs);
		assertEquals(2, testVet.getSpecialtiesInternal().size());

	}

	@Test
	void testAddSpecialtyValid() {
		// TODO Filip
		testVet.addSpecialty(spc);
		assertEquals(true, testVet.getSpecialtiesInternal().contains(spc));

	}

	@Test
	void testAddSpecialtyDuplicate() {
		// TODO Filip
		Set<Specialty> specialties = new HashSet<Specialty>();
		specialties.add(spc);
		specialties.add(spc);
		testVet.setSpecialtiesInternal(specialties);
		assertEquals(testVet.getSpecialtiesInternal().size(), 1);

	}

}
