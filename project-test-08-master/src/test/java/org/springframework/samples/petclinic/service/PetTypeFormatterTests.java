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

package org.springframework.samples.petclinic.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.persistence.PetRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

/**
 * Test class for {@link PetTypeFormatter}
 *
 * @author Colin But
 */
@ExtendWith(MockitoExtension.class)
class PetTypeFormatterTests {

	@Mock
	private PetRepository pets;

	private PetTypeFormatter petTypeFormatter;

	@BeforeEach
	void setup() {
		this.petTypeFormatter = new PetTypeFormatter(pets);
	}

	@Test
	void testPrint() {
		PetType petType = new PetType();
		petType.setName("Hamster");
		String petTypeName = this.petTypeFormatter.print(petType, Locale.ENGLISH);
		assertThat(petTypeName).isEqualTo("Hamster");
	}

	@Test
	void shouldParse() throws ParseException {
		given(this.pets.findPetTypes()).willReturn(makePetTypes());
		PetType petType = petTypeFormatter.parse("Bird", Locale.ENGLISH);
		assertThat(petType.getName()).isEqualTo("Bird");
	}

	@Test
	void shouldThrowParseException() throws ParseException {
		given(this.pets.findPetTypes()).willReturn(makePetTypes());
		assertThrows(ParseException.class, () -> {
			petTypeFormatter.parse("Fish", Locale.ENGLISH);
		});
	}

	@Test
	void testPrintValid() {

		given(this.pets.findPetTypes()).willReturn(makePetTypes());

		for (PetType pT : this.pets.findPetTypes()) {
			assertEquals(petTypeFormatter.print(pT, Locale.ENGLISH), pT.getName());
		}
	}

	@Test
	void testPrintPetTypeValid() {
		// TODO Ruixin
		PetType petType = new PetType();
		petType.setName("Dog");
		String petTypeName = this.petTypeFormatter.print(petType, Locale.ENGLISH);
		assertEquals(petTypeName, "Dog");
	}

	@Test
	void testPrintPetTypeInvalid() {
		// TODO Ruixin
		PetType petType = new PetType();
		String petTypeName = this.petTypeFormatter.print(petType, Locale.ENGLISH);
		assertEquals(petTypeName, null);
	}

	@Test
	void testPrintLocaleValid() {
		// TODO Linwei
		// create a new petType
		PetType petType = new PetType();
		petType.setName("Hamster");
		String petTypeName = this.petTypeFormatter.print(petType, InputData.locale);
		assertThat(petTypeName).isEqualTo("Hamster");
	}

	@Test
	void testPrintPetTypeNull() {
		// TODO Linwei
		// pet type null is not handled
		Exception exception = assertThrows(NullPointerException.class, () -> {
			this.petTypeFormatter.print(null, InputData.locale);
		});
	}

	@Test
	void testParseValid() throws ParseException {
		// TODO Linwei
		given(this.pets.findPetTypes()).willReturn(makePetTypes());
		PetType petType = petTypeFormatter.parse("Bird", InputData.locale);
		assertThat(petType.getName()).isEqualTo("Bird");
	}

	@Test
	void testParseTextNull() {
		// TODO Mohamed
		boolean fail = false;
		given(this.pets.findPetTypes()).willReturn(makePetTypes());
		try {
			PetType petType = petTypeFormatter.parse("", InputData.locale);
		}
		catch (Exception e) {
			fail = true;
			assertThat(fail).isEqualTo(true);
			assertThat(e.getMessage()).isEqualTo("type not found: ");
		}
	}

	@Test
	void testParseLocaleNull() throws ParseException {
		// TODO Zhanna
		given(this.pets.findPetTypes()).willReturn(makePetTypes());
		PetType petType = petTypeFormatter.parse("Bird", null);
		assertThat(petType.getName()).isEqualTo("Bird");
	}

	/**
	 * Helper method to produce some sample pet types just for test purpose
	 * @return {@link Collection} of {@link PetType}
	 */
	private List<PetType> makePetTypes() {
		List<PetType> petTypes = new ArrayList<>();
		petTypes.add(new PetType() {
			{
				setName("Dog");
			}
		});
		petTypes.add(new PetType() {
			{
				setName("Bird");
			}
		});
		return petTypes;
	}

}
