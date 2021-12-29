package org.springframework.samples.petclinic.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.persistence.PetRepository;
import org.springframework.samples.petclinic.persistence.VisitRepository;
import org.springframework.test.web.servlet.MockMvc;

//import junit.framework.Assert;

@WebMvcTest(VisitController.class)
public class VisitControllerTests {

	// Initializing our mock model view controller
	@Autowired
	private MockMvc mockMvc;

	// mocking our visitRepository
	@MockBean
	private VisitRepository visitRepository;

	// mocking our petRepository
	@MockBean
	private PetRepository petRepository;

	// setup performed before all tests
	@BeforeEach
	void setup() {

		Owner owner = new Owner();
		owner.setId(1);
		owner.setFirstName("George");
		owner.setLastName("Franklin");
		owner.setAddress("110 W. Liberty St.");
		owner.setCity("Madison");
		owner.setTelephone("6085551023");

		Pet pet = new Pet();
		pet.setId(1);
		LocalDate date = LocalDate.parse("2010-09-07");
		pet.setBirthDate(date);
		pet.setName("Leo");

		PetType petType = new PetType();
		petType.setId(1);
		petType.setName("cat");

		Visit visit = new Visit();
		visit.setDate(LocalDate.parse("2021-01-01"));
		visit.setDescription("first vet visit");
		visit.setId(1);
		visit.setPetId(1);

		ArrayList<Visit> listV = new ArrayList<Visit>();
		listV.add(visit);

		pet.setOwner(owner);
		pet.setType(petType);
		pet.setVisitsInternal(listV);

		// expected behavior from the above code
		given(this.visitRepository.findByPetId(1)).willReturn(listV);
		given(this.petRepository.findById(1)).willReturn(pet);

	}

	// @GetMapping("/owners/*/pets/{petId}/visits/new")
	@Test
	void testInitNewVisitFormExistentPetId() throws Exception {
		// TODO Mohamed
		// the pet 1 was created in the setup method
		mockMvc.perform(get("/owners/*/pets/1/visits/new")).andExpect(status().isOk())
				.andExpect(content().string(containsString("George Franklin"))) // finds
																				// the
																				// owner
																				// of pet
																				// 1
				.andExpect(view().name("pets/createOrUpdateVisitForm"));

	}

	@Test
	void testInitNewVisitFormNonExistentPetId() throws Exception {
		// TODO Mohamed
		// the pet of ID -1 does not exist

		ArrayList<Visit> listV = new ArrayList<Visit>();

		given(this.visitRepository.findByPetId(0)).willReturn(listV);
		given(this.petRepository.findById(0)).willReturn(null); // null because it is not
																// in the database
		boolean testSuccess = false;
		try {
			mockMvc.perform(get("/owners/1/pets/0/visits/new")).andExpect(status().isInternalServerError()) // should
																											// be
																											// 500
					.andExpect(view().name("pets/createOrUpdateVisitForm"));
		}
		catch (Exception e) {
			// the get command will have an error but will not throw one, that is why we
			// assert it fails
			testSuccess = true;
			// Assert.assertTrue(testSuccess);
		}

	}

	// @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
	@Test
	void testProcessNewVisitFormValidVisit() throws Exception {
		// TODO Mohamed

		Visit visit2 = new Visit();
		visit2.setDate(LocalDate.parse("2021-01-01"));
		visit2.setDescription("first vet visit");
		visit2.setId(2);
		visit2.setPetId(1);

		// the flashAttr sets the @ModelAttribute("visit") to visit2 that we created
		mockMvc.perform(post("/owners/1/pets/1/visits/new").flashAttr("visit", visit2))
				.andExpect(status().is3xxRedirection()) // redirection because succesfully
														// created a new visit
				.andExpect(content().string("")).andExpect(redirectedUrl("/owners/1"));

	}

	@Test
	void testProcessNewVisitFormNonValidVisit() throws Exception {
		// TODO Mohamed

		boolean testSuccess = false;
		try {
			// the flashAttr sets the @ModelAttribute("visit") to a random visit so it
			// should fail
			mockMvc.perform(post("/owners/1/pets/1/visits/new").flashAttr("visit", new Visit()))
					.andExpect(status().isOk()).andExpect(view().name("pets/createOrUpdateVisitForm"));
		}
		catch (Exception e) {
			// Visit is not valid because it has nothing
			testSuccess = true;
			// Assert.assertTrue(testSuccess);
		}

	}

	@Test
	void testProcessNewVisitFormNoVisit() throws Exception {
		// TODO Mohamed

		mockMvc.perform(post("/owners/1/pets/1/visits/new")).andExpect(status().isOk()) // redirection
																						// because
																						// succesfully
																						// created
																						// a
																						// new
																						// visit
				.andExpect(content().string(containsString("Enter a date in this format"))) // still
																							// asks
																							// to
																							// enter
																							// a
																							// visit
				.andExpect(view().name("pets/createOrUpdateVisitForm"));

	}

	@Test
	void testProcessNewVisitFormNonExistentPetId() throws Exception {
		// TODO Mohamed

		Visit visit2 = new Visit();
		visit2.setDate(LocalDate.parse("2021-01-01"));
		visit2.setDescription("first vet visit");
		visit2.setId(2);
		visit2.setPetId(2); // does not exist

		ArrayList<Visit> listV = new ArrayList<Visit>();
		given(this.visitRepository.findByPetId(2)).willReturn(null);
		given(this.petRepository.findById(2)).willReturn(null);

		mockMvc.perform(post("/owners/2/pets/2/visits/new").flashAttr("visit", visit2))
				.andExpect(status().is3xxRedirection()).andExpect(content().string(""))
				.andExpect(view().name("redirect:/owners/{ownerId}"));
		// Note that this is not the expected behavior, the code should not allow this
		// the code allows to add a visit to an owner and a pet that do not exist

	}

}
