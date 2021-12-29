package org.springframework.samples.petclinic.model_integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.persistence.OwnerRepository;
import org.springframework.samples.petclinic.persistence.PetRepository;
import org.springframework.samples.petclinic.persistence.VisitRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class PetTests {

	private static final Integer PET_ID = 15;

	private static final Integer OWNER_ID = 30;

	private static final String OWNER_ADDRESS = "7501 rue Rousselot";

	private static final String OWNER_CITY = "Villeray";

	private static final String OWNER_FIRST_NAME = "Yacine";

	private static final String OWNER_LAST_NAME = "Villeray";

	private static final String TELEPHONE = "514-495-0371";

	@Mock
	private PetRepository petRepository;

	@Mock
	private OwnerRepository ownerRepository;

	@Mock
	private VisitRepository visitRepository;

	@InjectMocks
	private static Pet pet;

	@InjectMocks
	private static Owner owner;

	@InjectMocks
	private static Visit visit;

	@BeforeEach
	public void setUp() throws Exception {

		// mock pet
		lenient().when(petRepository.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(PET_ID)) {
				Pet p = new Pet();
				Owner o = new Owner();
				o.setId(OWNER_ID);
				o.setAddress(OWNER_ADDRESS);
				o.setCity(OWNER_CITY);
				o.setFirstName(OWNER_FIRST_NAME);
				o.setLastName(OWNER_LAST_NAME);
				o.setTelephone(TELEPHONE);
				Set<Pet> set = new HashSet<Pet>();
				set.add(p);
				o.setPetsInternal(set);
				p.setOwner(o);
				p.setId(PET_ID);
				p.setName("Leo");
				p.setBirthDate(LocalDate.of(2020, 1, 8));
				PetType pT = new PetType();
				pT.setId(20);
				pT.setName("Alpha");
				p.setType(pT);

				pet = p;
				owner = o;

				return p;
			}
			else {
				return null;
			}
		});

	}

	@Test
	void testGetType() {
		Pet p = petRepository.findById(PET_ID);
		// System.out.println(p.getType().getName() + " / " + p.getType().getId());
		assertEquals(pet.getType(), p.getType());
		assertEquals(pet.getType().getName(), p.getType().getName());
		assertEquals(pet.getType().getId(), p.getType().getId());

	}

	@Test
	void testSetTypeValid() {

		Pet p = petRepository.findById(PET_ID);
		PetType petTypeValid = new PetType();
		petTypeValid.setId(100);
		petTypeValid.setName("Beta");
		p.setType(petTypeValid);

		assertEquals(p.getType().getId(), 100);
		assertEquals(p.getType().getName(), "Beta");
	}

	@Test
	void testSetTypeNull() {
		Pet p = petRepository.findById(PET_ID);
		PetType petTypeNull = null;
		p.setType(petTypeNull);

		assertNull(p.getType());
	}

	@Test
	void testGetOwnerExistentOwner() {
		Pet p = petRepository.findById(PET_ID);
		assertEquals(pet.getOwner(), p.getOwner());
		assertEquals(OWNER_ID, p.getOwner().getId());
		assertEquals(OWNER_ADDRESS, p.getOwner().getAddress());
		assertEquals(OWNER_CITY, p.getOwner().getCity());
		assertEquals(OWNER_FIRST_NAME, p.getOwner().getFirstName());
		assertEquals(OWNER_LAST_NAME, p.getOwner().getLastName());

	}

	@Test
	void testGetOwnerNull() {
		assertNull(ownerRepository.findById(anyInt()));
	}

	@Test
	void testSetOwner() {
		// TODO Mohamed
		Pet p = petRepository.findById(PET_ID);
		Owner ownerValid = new Owner();
		ownerValid.setId(23);
		ownerValid.setFirstName("Keith");
		ownerValid.setLastName("Jarrett");
		ownerValid.setAddress("52nd Street");
		ownerValid.setCity("New York");
		ownerValid.setTelephone("2120990983");
		p.setOwner(ownerValid);

		assertEquals(p.getOwner().getId(), 23);
		assertEquals(p.getOwner().getFirstName(), "Keith");
		assertEquals(p.getOwner().getLastName(), "Jarrett");
		assertEquals(p.getOwner().getCity(), "New York");
	}

	@Test
	void testSetVisitsInternal() {
		// TODO Mohamed
		Pet p = petRepository.findById(PET_ID);
		Collection<Visit> visits = new LinkedHashSet<>();

		assertEquals(visits.size(), 0);

		Visit visit1 = new Visit();
		visit1.setId(100);
		visit1.setDescription("another description1");
		visit1.setDate(LocalDate.of(2021, 11, 01));
		visits.add(visit1);
		Visit visit2 = new Visit();
		visit2.setId(200);
		visit2.setDescription("another description2");
		visit2.setDate(LocalDate.of(2021, 11, 02));
		visits.add(visit2);
		p.setVisitsInternal(visits);

		assertEquals(visits.size(), 2);

	}

	@Test
	void testGetVisitsInternalExistentVisits() {
		// TODO Mohamed
		Pet p = petRepository.findById(PET_ID);
		Collection<Visit> visits = new LinkedHashSet<>();

		Visit visit1 = new Visit();
		visit1.setId(100);
		visit1.setDescription("another description1");
		visit1.setDate(LocalDate.of(2021, 11, 01));
		visits.add(visit1);
		Visit visit2 = new Visit();
		visit2.setId(200);
		visit2.setDescription("another description2");
		visit2.setDate(LocalDate.of(2021, 11, 02));
		visits.add(visit2);
		p.setVisitsInternal(visits);

		assertEquals(p.getVisitsInternal().size(), 2);
	}

	@Test
	void testGetVisitsInternalNull() { // Here we're assuming that there are no visits in
										// db at all
		// TODO Mohamed
		Pet p = petRepository.findById(PET_ID);
		assertEquals(p.getVisitsInternal(), new HashSet<Visit>());
	}

	@Test
	void testGetVisits() {
		// TODO Linwei
		Pet p = petRepository.findById(PET_ID);
		Collection<Visit> visits = new LinkedHashSet<>();

		Visit visit1 = new Visit();
		visit1.setId(100);
		visit1.setDescription("another description1");
		visit1.setDate(LocalDate.of(2021, 11, 01));
		visits.add(visit1);
		Visit visit2 = new Visit();
		visit2.setId(200);
		visit2.setDescription("another description2");
		visit2.setDate(LocalDate.of(2021, 11, 02));
		visits.add(visit2);
		p.setVisitsInternal(visits);

		assertEquals(p.getVisits().size(), 2);
		assertEquals(p.getVisits().get(1).getDate(), visit1.getDate());
		assertEquals(p.getVisits().get(1).getDescription(), visit1.getDescription());
		assertEquals(p.getVisits().get(1).getPetId(), visit1.getPetId());
		assertEquals(p.getVisits().get(0).getPetId(), visit2.getPetId());
		assertEquals(p.getVisits().get(0).getDate(), visit2.getDate());
		assertEquals(p.getVisits().get(0).getDescription(), visit2.getDescription());

	}

	@Test
	void testAddVisit() {
		// TODO Linwei
		Pet p = petRepository.findById(PET_ID);
		Visit visitValid = new Visit();
		visitValid.setId(100);
		visitValid.setDescription("another description1");
		visitValid.setDate(LocalDate.of(2021, 11, 01));
		p.addVisit(visitValid);
		assertEquals(p.getVisits().get(0).getDate(), visitValid.getDate());
		assertEquals(p.getVisits().get(0).getDescription(), visitValid.getDescription());
		assertEquals(p.getVisits().get(0).getPetId(), visitValid.getPetId());
	}

	@Test
	void testAddVisitNull() {
		// TODO Linwei
		Pet p = petRepository.findById(PET_ID);
		// pet type null is not handled
		Exception exception = assertThrows(NullPointerException.class, () -> {
			p.addVisit(null);
		});
	}

	@Test
	void testAddVisitDuplicate() {
		// TODO Linwei
		// the model does not handle duplicate visits
		// it would add the duplicate visit as a normal visit
		Pet p = petRepository.findById(PET_ID);
		Visit visit1 = new Visit();
		visit1.setId(100);
		visit1.setDescription("another description1");
		visit1.setDate(LocalDate.of(2021, 11, 01));
		p.addVisit(visit1);
		Visit visit2 = new Visit();
		visit2.setId(100);
		visit2.setDescription("another description1");
		visit2.setDate(LocalDate.of(2021, 11, 01));
		p.addVisit(visit2);

		assertEquals(p.getVisits().size(), 2);
		assertEquals(p.getVisits().get(1).getDate(), visit1.getDate());
		assertEquals(p.getVisits().get(1).getDescription(), visit1.getDescription());
		assertEquals(p.getVisits().get(1).getPetId(), visit1.getPetId());
		assertEquals(p.getVisits().get(0).getPetId(), visit2.getPetId());
		assertEquals(p.getVisits().get(0).getDate(), visit2.getDate());
		assertEquals(p.getVisits().get(0).getDescription(), visit2.getDescription());
	}

	@Test
	void testAddVisitDayThatAlreadyPassed() {
		// TODO Linwei
		// passed date is not handled by the model
		Pet p = petRepository.findById(PET_ID);
		Visit visitValid = new Visit();
		visitValid.setId(100);
		visitValid.setDescription("another description1");
		// an ancient date
		visitValid.setDate(LocalDate.of(2000, 11, 01));
		p.addVisit(visitValid);
		assertEquals(p.getVisits().get(0).getDate(), visitValid.getDate());
		assertEquals(p.getVisits().get(0).getDescription(), visitValid.getDescription());
		assertEquals(p.getVisits().get(0).getPetId(), visitValid.getPetId());
	}

}
