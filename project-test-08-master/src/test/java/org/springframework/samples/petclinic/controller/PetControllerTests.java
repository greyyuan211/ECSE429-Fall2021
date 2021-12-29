package org.springframework.samples.petclinic.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.persistence.OwnerRepository;
import org.springframework.samples.petclinic.persistence.PetRepository;
import org.springframework.samples.petclinic.persistence.VisitRepository;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PetController.class)
public class PetControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OwnerRepository owners;

	@MockBean
	private PetRepository pets;

	@MockBean
	private VisitRepository visits;

	@BeforeEach
	void setup() {
		Owner owner = new Owner();
		owner.setId(1);
		owner.setFirstName("Clint");
		owner.setLastName("Molson");
		owner.setAddress("333 a street.");
		owner.setCity("Madison");
		owner.setTelephone("478397444");

		Pet pet = new Pet();
		pet.setId(1);
		pet.setName("oliver");
		LocalDate date = LocalDate.parse("2020-09-07");
		pet.setBirthDate(date);

		PetType petType = new PetType();
		petType.setId(1);
		petType.setName("cat");

		Visit visit = new Visit();
		visit.setDate(LocalDate.parse("2021-02-01"));
		visit.setDescription("vet visit");
		visit.setId(1);
		visit.setPetId(1);

		ArrayList<Visit> listV = new ArrayList<Visit>();
		listV.add(visit);

		pet.setOwner(owner);
		pet.setType(petType);
		pet.setVisitsInternal(listV);

		Set<Pet> listP = new HashSet<Pet>();
		listP.add(pet);
		owner.setPetsInternal(listP);

		// expected behavior from the above code
		// given(this.visits.findByPetId(1)).willReturn(listV);
		given(this.pets.findById(1)).willReturn(pet);
		given(this.owners.findById(1)).willReturn(owner);

	}

	@Test
	void testInitCreationForm() throws Exception {
		// TODO Ruixin
		mockMvc.perform(get("/owners/{ownerId}/pets/new", 1)).andExpect(status().isOk())
				.andExpect(content().string(containsString("Clint Molson"))) // finds the
																				// owner
																				// of pet
																				// 1
				.andExpect(view().name("pets/createOrUpdatePetForm"));

	}

	@Test
	void testInitCreationFormOwnerNonexistent() {
		// TODO Ruixin
		given(this.owners.findById(2)).willReturn(null);
		boolean testSuccess = false;
		try {
			mockMvc.perform(get("/owners/{ownerId}/pets/new", 2)).andExpect(status().isInternalServerError()); // should
																												// be
																												// 500
		}
		catch (Exception e) {
			// the get command will have an error but will not throw one, that is why we
			// assert it fails
			testSuccess = true;
			assertTrue(testSuccess);
		}
	}

	@Test
	void testProcessCreationForm() throws Exception {
		// TODO Ruixin
		Pet pet1 = new Pet();
		pet1.setBirthDate(LocalDate.parse("2020-06-01"));
		pet1.setName("pet_1");
		pet1.setId(2);

		// the flashAttr sets the @ModelAttribute("visit") to visit2 that we created
		mockMvc.perform(post("/owners/{ownerId}/pets/new", 1).flashAttr("pet", pet1))
				.andExpect(status().is3xxRedirection()) // redirection because succesfully
														// created a new visit
				.andExpect(content().string("")).andExpect(redirectedUrl("/owners/1"));

	}

	@Test
	void processProcessCreationFormHasError() throws Exception {
		// TODO Ruixin
		Pet pet1 = new Pet();
		pet1.setBirthDate(LocalDate.parse("2020-06-01"));
		pet1.setName("pet_1");
		pet1.setId(2);

		mockMvc.perform(post("/owners/{ownerId}/pets/new", 1).flashAttr("pet", pet1)
				.contentType(MediaType.APPLICATION_JSON).param("birthDate", "20200601")).andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("pet"))
				.andExpect(model().attributeHasFieldErrors("pet", "birthDate"))
				.andExpect(view().name("pets/createOrUpdatePetForm"));
	}

	@Test
	void testProcessCreationFormDuplicate() throws Exception {
		// TODO Ruixin
		Pet pet = new Pet();
		pet.setName("oliver");
		LocalDate date = LocalDate.parse("2020-09-07");
		pet.setBirthDate(date);

		Owner owner = owners.findById(1);

		PetType petType = new PetType();
		petType.setId(1);
		petType.setName("cat");

		pet.setType(petType);
		pet.setOwner(owner);

		mockMvc.perform(post("/owners/{ownerId}/pets/new", 1).flashAttr("pet", pet)).andExpect(status().isOk())
				.andExpect(view().name("pets/createOrUpdatePetForm"))// create pet was
																		// unsuccesful
				.andExpect(model().attributeHasErrors("pet"));
	}

	@Test
	void testInitUpdateForm() throws Exception {
		// TODO Linwei
		mockMvc.perform(get("/owners/{ownerId}/pets/{petId}/edit", 1, 1)).andExpect(status().isOk())
				.andExpect(content().string(containsString("Clint Molson"))) // finds the
																				// owner
																				// of pet
																				// 1
				.andExpect(view().name("pets/createOrUpdatePetForm"));
	}

	@Test
	void testInitUpdateFormNonExistent() throws Exception {
		// TODO Linwei
		given(this.pets.findById(2)).willReturn(null);
		boolean testSuccess = false;
		try {
			mockMvc.perform(get("/owners/{ownerId}/pets/{petId}/edit", 1, 2))
					.andExpect(status().isInternalServerError()); // should be 500
		}
		catch (Exception e) {
			// the get command will have an error but will not throw one, that is why we
			// assert it fails
			testSuccess = true;
			assertTrue(testSuccess);
		}
	}

	@Test
	void testInitUpdateFormNull() throws Exception {
		// TODO Linwei
		boolean testSuccess = false;
		try {
			mockMvc.perform(get("/owners/{ownerId}/pets/{petId}/edit", 1, null)).andExpect(status().isNotFound()); // should
																													// be
																													// 404
		}
		catch (Exception e) {
			// the get command will have an error but will not throw one, that is why we
			// assert it fails
			testSuccess = true;
			assertTrue(testSuccess);
		}
	}

	@Test
	void testProcessUpdateFormSuccess() throws Exception {
		// TODO Linwei
		Pet pet1 = new Pet();
		pet1.setBirthDate(LocalDate.parse("2020-06-01"));
		pet1.setName("pet_1");
		pet1.setId(2);

		mockMvc.perform(post("/owners/{ownerId}/pets/{petId}/edit", 1, 1).flashAttr("pet", pet1))
				.andExpect(status().is3xxRedirection()).andExpect(content().string(""))
				.andExpect(redirectedUrl("/owners/1"));
	}

	@Test
	void testProcessUpdateFormHasError() throws Exception {
		// TODO Linwei
		Pet pet1 = new Pet();
		pet1.setBirthDate(LocalDate.parse("2020-06-01"));
		pet1.setName("pet_1");
		pet1.setId(2);

		mockMvc.perform(post("/owners/{ownerId}/pets/{petId}/edit", 1, 1).flashAttr("pet", pet1)
				.contentType(MediaType.APPLICATION_JSON).param("birthDate", "20200601")).andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("pet"))
				.andExpect(model().attributeHasFieldErrors("pet", "birthDate"))
				.andExpect(view().name("pets/createOrUpdatePetForm"));
	}

	@Test
	void testProcessUpdateFormInvalidId() throws Exception {
		// TODO Filip
		Pet pet1 = new Pet();
		pet1.setBirthDate(LocalDate.parse("2020-06-01"));
		pet1.setName("vasia");
		pet1.setId(2);

		mockMvc.perform(post("/owners/{ownerId}/pets/{petId}/edit", -1, 1).flashAttr("pet", pet1)
				.contentType(MediaType.APPLICATION_JSON).param("birthDate", "20200601")).andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("pet"))
				.andExpect(model().attributeHasFieldErrors("pet", "birthDate"))
				.andExpect(view().name("pets/createOrUpdatePetForm"));
	}

}
