/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.model;

import org.junit.jupiter.api.Test;
import org.springframework.util.SerializationUtils;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Dave Syer
 */
class VetTests {

	Vets testVets;

	@Test
	void testSerialization() {
		Vet vet = new Vet();
		vet.setFirstName("Zaphod");
		vet.setLastName("Beeblebrox");
		vet.setId(123);
		Vet other = (Vet) SerializationUtils.deserialize(SerializationUtils.serialize(vet));
		assertThat(other.getFirstName()).isEqualTo(vet.getFirstName());
		assertThat(other.getLastName()).isEqualTo(vet.getLastName());
		assertThat(other.getId()).isEqualTo(vet.getId());
	}

	@Test
	void testGetSpecialtiesInternal() {
		// TODO Zhanna
		Vet vet = new Vet();
		Specialty specialty1 = new Specialty();
		specialty1.setName(InputData.specialty);
		Specialty specialty2 = new Specialty();
		specialty2.setName(InputData.specialty2);
		Set<Specialty> specialties = new HashSet<Specialty>();
		specialties.add(specialty1);
		specialties.add(specialty2);
		vet.setSpecialtiesInternal(specialties);

		assertEquals(vet.getSpecialtiesInternal().size(), 2);
	}

	@Test
	void testGetSpecialtiesInternalNull() {
		// TODO Zhanna
		Vet vet = new Vet();
		Set<Specialty> specialties;

		assertThat(vet.getSpecialtiesInternal().isEmpty());
	}

	@Test
	void testGetSpecialties() {
		// TODO Zhanna
		Vet vet = new Vet();
		Specialty specialty1 = new Specialty();
		specialty1.setName(InputData.specialty);
		Specialty specialty2 = new Specialty();
		specialty2.setName(InputData.specialty2);
		Specialty specialty3 = new Specialty();
		specialty2.setName(InputData.specialty3);
		Set<Specialty> specialties = new HashSet<Specialty>();
		specialties.add(specialty1);
		specialties.add(specialty2);
		specialties.add(specialty3);
		vet.setSpecialtiesInternal(specialties);

		assertEquals(vet.getSpecialties().size(), 3);

	}

	@Test
	void testGetNrOfSpecialties() {
		// TODO Zhanna
		Vet vet = new Vet();
		Specialty specialty1 = new Specialty();
		specialty1.setName(InputData.specialty);
		Specialty specialty2 = new Specialty();
		specialty2.setName(InputData.specialty2);
		Specialty specialty3 = new Specialty();
		specialty2.setName(InputData.specialty3);
		Set<Specialty> specialties = new HashSet<Specialty>();
		specialties.add(specialty1);
		specialties.add(specialty2);
		specialties.add(specialty3);
		vet.setSpecialtiesInternal(specialties);

		assertEquals(vet.getNrOfSpecialties(), 3);
	}

	@Test
	void testAddSpecialty() {
		// TODO Zhanna
		Vet vet = new Vet();
		Specialty specialty1 = new Specialty();
		specialty1.setName(InputData.specialty);
		Specialty specialty2 = new Specialty();
		specialty2.setName(InputData.specialty2);
		Specialty specialty3 = new Specialty();
		specialty2.setName(InputData.specialty3);
		vet.addSpecialty(specialty1);
		vet.addSpecialty(specialty2);
		vet.addSpecialty(specialty3);

		assertEquals(vet.getNrOfSpecialties(), 3);
	}

}
