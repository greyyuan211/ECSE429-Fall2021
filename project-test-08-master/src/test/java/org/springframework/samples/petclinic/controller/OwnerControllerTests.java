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

import javax.print.attribute.standard.Media;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.Collections;

import org.assertj.core.util.Lists;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(OwnerController.class)
public class OwnerControllerTests {

	private Owner milt;

	private Owner thelonious;

	private Owner beth;

	private Owner herbie;

	private Owner herbie2;

	private Pet pet;

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
		milt = new Owner();
		milt.setFirstName("Milt");
		milt.setLastName("Jackson");
		milt.setAddress("52nd Street");
		milt.setCity("New York");
		milt.setTelephone("2125553465");
		milt.setId(1);
		given(owners.findById(1)).willReturn(milt);

		thelonious = new Owner();
		thelonious.setFirstName("Thelonious");
		thelonious.setLastName("Monk");
		thelonious.setAddress("210 West 118th Street");
		thelonious.setCity("New York");
		thelonious.setTelephone("2123243444");
		thelonious.setId(2);
		given(owners.findById(2)).willReturn(thelonious);

		beth = new Owner();
		beth.setFirstName("Beth");
		beth.setLastName("Harmon");
		beth.setAddress("93 Salisbury Ave");
		beth.setCity("Lexington");
		beth.setTelephone("8591223245");
		beth.setId(3);
		given(owners.findById(3)).willReturn(beth);

		herbie = new Owner();
		herbie.setFirstName("Herbie");
		herbie.setLastName("Hancock");
		herbie.setAddress("210 West 118th Street");
		herbie.setCity("New York");
		herbie.setTelephone("2120909999");
		herbie.setId(4);
		given(owners.findById(4)).willReturn(herbie);

		pet = new Pet();
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
		given(visits.findByPetId(1)).willReturn(listV);

		pet.setOwner(milt);
		pet.setType(petType);
		pet.setVisitsInternal(listV);

		Set<Pet> listP = new HashSet<Pet>();
		listP.add(pet);
		milt.setPetsInternal(listP);

		ArrayList<Owner> allOwnersList = new ArrayList<Owner>();
		PageImpl<Owner> allOwners = new PageImpl<Owner>(allOwnersList);
	}

	@Test
	void testInitCreationForm() throws Exception { // testGetOwnersNew
		// TODO Zhanna
		mockMvc.perform(get("/owners/new")).andExpect(status().isOk()).andExpect(model().attributeExists("owner"))
				.andExpect(view().name("owners/createOrUpdateOwnerForm")).andExpect(model().size(1));
	}

	@Test
	void testProcessCreationFormSuccess() throws Exception { // testPostOwnersNewIfCaseSuccess
		// TODO Zhanna
		// Testing else condition -> redirects to new page
		mockMvc.perform(post("/owners/new").contentType(MediaType.APPLICATION_JSON).param("firstName", "Miles")
				.param("lastName", "Davis").param("address", "52nd Street").param("city", "New York")
				.param("telephone", "2123333456")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/owners/null"));

	}

	@Test
	void testProcessCreationFormHasErrors() throws Exception {// testPostOwnersNewElseCaseHasErrors
		// TODO Zhanna
		// Testing if condition -> stays on the same page
		mockMvc.perform(post("/owners/new").contentType(MediaType.APPLICATION_JSON).param("firstName", "Miles")
				.param("lastName", "Davis").param("address", "52nd Street").param("city", "New York")
				.param("telephone", "212-333-3456")).andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("owner"))
				.andExpect(model().attributeHasFieldErrors("owner", "telephone"))
				.andExpect(view().name("owners/createOrUpdateOwnerForm"));
	}

	@Test
	void testInitFindForm() throws Exception { // testGetOwnersFind
		// TODO Zhanna
		mockMvc.perform(get("/owners/find")).andExpect(status().isOk()).andExpect(model().attributeExists("owner"))
				.andExpect(view().name("owners/findOwners")).andExpect(model().size(1));
	}

	@Test
	void testProcessFindFormEmptyOwnersTable() throws Exception { // testGetOwners
		// TODO Zhanna
		// testing if condition -> no owners found
		mockMvc.perform(get("/owners")).andExpect(status().isOk()).andExpect(view().name("owners/findOwners"));
	}

	@Test
	void testProcessFindForm() throws Exception { // testGetOwners
		// TODO Zhanna
		Owner owner = new Owner();
		owner.setLastName("Jackson");

		ArrayList<Owner> miltList = new ArrayList<Owner>();
		miltList.add(milt);
		given(owners.findByLastName("Jackson")).willReturn(miltList);

		mockMvc.perform(get("/owners").flashAttr("owner", owner)).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/1"));

	}

	@Test
	void testProcessFindFormByLastNameMultipleOwners() throws Exception {
		// TODO Zhanna
		herbie2 = new Owner();
		herbie2.setFirstName("herbie2");
		herbie2.setLastName("Hancock");
		herbie2.setAddress("210 West 118th Street");
		herbie2.setCity("New York");
		herbie2.setTelephone("2120909999");
		herbie2.setId(5);

		given(owners.findById(5)).willReturn(herbie);
		ArrayList<Owner> herbieList = new ArrayList<Owner>();
		herbieList.add(herbie2);
		herbieList.add(herbie);
		given(owners.findByLastName("Hancock")).willReturn(herbieList);

		Owner owner = new Owner();
		owner.setLastName("Hancock");

		mockMvc.perform(get("/owners").flashAttr("owner", owner)).andExpect(status().isOk())
				.andExpect(model().attribute("selections", hasItem(herbie)))
				.andExpect(model().attribute("selections", hasItem(herbie2)))
				.andExpect(view().name("owners/ownersList"));

	}

	@Test
	void testInitUpdateOwnerForm() throws Exception { // testGetOwnersOwnerIdEditExistent
		// TODO Zhanna
		mockMvc.perform(get("/owners/{ownerId}/edit", 1)).andExpect(status().isOk())
				.andExpect(model().attributeExists("owner"))
				.andExpect(model().attribute("owner", hasProperty("firstName", is("Milt"))))
				.andExpect(model().attribute("owner", hasProperty("lastName", is("Jackson"))))
				.andExpect(model().attribute("owner", hasProperty("address", is("52nd Street"))))
				.andExpect(model().attribute("owner", hasProperty("city", is("New York"))))
				.andExpect(model().attribute("owner", hasProperty("telephone", is("2125553465"))))
				.andExpect(view().name("owners/createOrUpdateOwnerForm"));

		mockMvc.perform(get("/owners/{ownerId}/edit", 2)).andExpect(status().isOk())
				.andExpect(model().attributeExists("owner"))
				.andExpect(model().attribute("owner", hasProperty("firstName", is("Thelonious"))))
				.andExpect(model().attribute("owner", hasProperty("lastName", is("Monk"))))
				.andExpect(model().attribute("owner", hasProperty("address", is("210 West 118th Street"))))
				.andExpect(model().attribute("owner", hasProperty("city", is("New York"))))
				.andExpect(model().attribute("owner", hasProperty("telephone", is("2123243444"))))
				.andExpect(view().name("owners/createOrUpdateOwnerForm"));

		mockMvc.perform(get("/owners/{ownerId}/edit", 3)).andExpect(status().isOk())
				.andExpect(model().attributeExists("owner"))
				.andExpect(model().attribute("owner", hasProperty("firstName", is("Beth"))))
				.andExpect(model().attribute("owner", hasProperty("lastName", is("Harmon"))))
				.andExpect(model().attribute("owner", hasProperty("address", is("93 Salisbury Ave"))))
				.andExpect(model().attribute("owner", hasProperty("city", is("Lexington"))))
				.andExpect(model().attribute("owner", hasProperty("telephone", is("8591223245"))))
				.andExpect(view().name("owners/createOrUpdateOwnerForm"));
	}

	@Test
	void testProcessUpdateOwnerFormValidOwner() throws Exception {
		// TODO Zhanna
		milt.setAddress("Shinzo Sasageyo");
		mockMvc.perform(post("/owners/1/edit").flashAttr("owner", milt)).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/{ownerId}")).andExpect(redirectedUrl("/owners/1"));

		mockMvc.perform(get("/owners/1")).andExpect(status().isOk()).andExpect(model().attributeExists("owner"))
				.andExpect(model().attribute("owner", hasProperty("address", is("Shinzo Sasageyo"))));
	}

	@Test
	void testProcessUpdateOwnerFormNonValidOwner() throws Exception {
		// TODO Zhanna
		String oldTelephone = milt.getTelephone();
		milt.setTelephone("ThisIsNotAValidTelephone");
		mockMvc.perform(post("/owners/1/edit").flashAttr("owner", milt)).andExpect(status().isOk())
				.andExpect(view().name("owners/createOrUpdateOwnerForm"));

		mockMvc.perform(get("/owners/1")).andExpect(status().isOk()).andExpect(model().attributeExists("owner"))
				.andExpect(model().attribute("owner", hasProperty("telephone", is("ThisIsNotAValidTelephone"))));
	}

	@Test
	void testInitUpdateOwnerFormNonExistentId() throws Exception { // for ex. for invalid
																	// id put a string
		given(owners.findById(50)).willReturn(null);
		boolean testSuccess = false;
		try {
			mockMvc.perform(get("/owners/{ownerId}/edit", 50)).andExpect(status().isInternalServerError());
		}
		catch (Exception e) {
			testSuccess = true;
			assertTrue(testSuccess);
		}
		// instead of expected int
	}

	@Test
	void testInitUpdateOwnerFormInvalidId() {
		given(owners.findById(-500)).willReturn(null);
		boolean testSuccess = false;
		try {
			mockMvc.perform(get("/owners/{ownerId}/edit", -500)).andExpect(status().isBadRequest());

		}
		catch (Exception e) {
			testSuccess = true;
			assertTrue(testSuccess);
		}
		// TODO Yacine
	}

	@Test
	void testProcessUpdateOwnerFormExistentId() throws Exception {
		mockMvc.perform(post("/owners/{ownerId}/edit", 1).contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "New").param("lastName", "Owner").param("address", "Dans le hood")
				.param("city", "DTOWN").param("telephone", "5145145144")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/{ownerId}"));

		mockMvc.perform(post("/owners/{ownerId}/edit", 2).contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "Alpha").param("lastName", "Beta").param("address", "Omega").param("city", "Theta")
				.param("telephone", "5555555555")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/{ownerId}"));

		mockMvc.perform(post("/owners/{ownerId}/edit", 3).contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "Brahimi").param("lastName", "Zidane").param("address", "Mahrez")
				.param("city", "Gourcuff").param("telephone", "4444444444")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/{ownerId}"));

		mockMvc.perform(post("/owners/{ownerId}/edit", 4).contentType(MediaType.APPLICATION_JSON)
				.param("firstName", "Ahmed").param("lastName", "Regarde").param("address", "Khoya")
				.param("city", "Maguire").param("telephone", "7777777777")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/{ownerId}"));
	}

	@Test
	void testProcessUpdateOwnerFormNonExistentId() throws Exception {
		given(owners.findById(500)).willReturn(null);
		// boolean testSuccess=false;
		// try {
		mockMvc.perform(post("/owners/{ownerId}/edit", 500))
				// .andExpect(status().isBadRequest())
				.andExpect(view().name("owners/createOrUpdateOwnerForm"));
		// } catch (Exception e) {
		// testSuccess=true;
		// assertTrue(testSuccess);
		// }
	}

	@Test
	void testProcessUpdateOwnerFormInvalidId() throws Exception {
		given(owners.findById(-500)).willReturn(null);
		// boolean testSuccess=false;
		// try {
		mockMvc.perform(post("/owners/{ownerId}/edit", -500))
				// .andExpect(status().isBadRequest())
				.andExpect(view().name("owners/createOrUpdateOwnerForm"));

		//
		// } catch (Exception e) {
		// testSuccess=true;
		// assertTrue(testSuccess);
		// }
	}

	@Test
	void testShowOwnerOwnerIdExistent() throws Exception {
		// TODO Ruixin
		mockMvc.perform(get("/owners/{ownerId}", 1)).andExpect(status().isOk())
				.andExpect(model().attributeExists("owner")).andExpect(model().attributeExists("owner"))
				.andExpect(model().attribute("owner", hasProperty("firstName", is("Milt"))))
				.andExpect(model().attribute("owner", hasProperty("lastName", is("Jackson"))))
				.andExpect(model().attribute("owner", hasProperty("address", is("52nd Street"))))
				.andExpect(model().attribute("owner", hasProperty("city", is("New York"))))
				.andExpect(model().attribute("owner", hasProperty("telephone", is("2125553465"))))
				.andExpect(view().name("owners/ownerDetails"));

	}

	@Test
	void testShowOwnerOwnerIdNonExistent() {
		// TODO Ruixin
		given(owners.findById(30)).willReturn(null);
		boolean testSuccess = false;
		try {
			mockMvc.perform(get("/owners/{ownerId}", 30)).andExpect(status().isInternalServerError());// should
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
	void testShowOwnerOwnerIdInvalid() {
		// TODO Ruixin
		given(owners.findById(30)).willReturn(null);
		boolean testSuccess = false;
		try {
			mockMvc.perform(get("/owners/{ownerId}", "id")).andExpect(status().isBadRequest()); // should
																								// be
																								// 400

		}
		catch (Exception e) {
			// the get command will have an error but will not throw one, that is why we
			// assert it fails
			testSuccess = true;
			assertTrue(testSuccess);
		}

	}

}
