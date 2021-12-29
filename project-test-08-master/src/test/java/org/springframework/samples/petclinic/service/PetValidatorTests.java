package org.springframework.samples.petclinic.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.persistence.PetRepository;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PetValidatorTests {

	@Mock
	private PetRepository pets;

	private PetValidator petValidator;

	@BeforeEach
	void setup() {
		petValidator = new PetValidator();
	}

	@Test
	void testValidateObjectValid() {
		// TODO Zhanna
		Pet pet = new Pet();
		PetType petType = new PetType();
		petType.setName(InputData.petTypeName);
		pet.setName(InputData.petName);
		pet.setType(petType);
		pet.setBirthDate(InputData.birthDate);
		Errors errors = new BeanPropertyBindingResult(pet, InputData.petName);
		petValidator.validate(pet, errors);
		assertFalse(errors.hasErrors());
	}

	@Test
	void testValidateObjectNull() {
		// TODO Zhanna
		Pet pet = new Pet();
		PetType petType = new PetType();
		petType.setName(null);
		pet.setName(null);
		pet.setType(petType);
		pet.setBirthDate(null);
		Errors errors = new BeanPropertyBindingResult(pet, InputData.petName);
		petValidator.validate(pet, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	void testValidatePetTypeNull() {
		// TODO Zhanna
		Pet pet = new Pet();
		pet.setName(null);
		pet.setBirthDate(null);
		Errors errors = new BeanPropertyBindingResult(pet, InputData.petName);
		petValidator.validate(pet, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	void testSupports() {
		// TODO Linwei
		// test a case that supports return true
		Class<?> myClassPet = Pet.class;
		Boolean resultTrue = petValidator.supports(myClassPet);
		// test a case that supports return false
		Class<?> myClassPetType = PetType.class;
		Boolean resultFalse = petValidator.supports(myClassPetType);
		assertTrue(resultTrue);
		assertFalse(resultFalse);
	}

}
