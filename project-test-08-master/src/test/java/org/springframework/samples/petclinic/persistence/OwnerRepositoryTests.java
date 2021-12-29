package org.springframework.samples.petclinic.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.samples.petclinic.model.Owner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class OwnerRepositoryTests {

	@Autowired
	private OwnerRepository ownerRepository;

	@Test
	void testFindByLastNameExistent() {
		String lastName = "Findable";
		Owner o = new Owner();
		o.setAddress("25 Bonjour");
		o.setCity("Montreal");
		o.setFirstName("Tamoulou");
		o.setLastName(lastName);
		o.setTelephone("5145145144");
		ownerRepository.save(o);
		o = null;

		List<Owner> list;
		if ((List<Owner>) ownerRepository.findByLastName(lastName) instanceof List)
			list = (List<Owner>) ownerRepository.findByLastName(lastName);
		else
			list = new ArrayList(ownerRepository.findByLastName(lastName));

		o = list.get(0);

		assertNotNull(list.get(0));
		assertEquals("Tamoulou", o.getFirstName());
		assertEquals("Montreal", o.getCity());
		assertEquals("25 Bonjour", o.getAddress());
		assertEquals("5145145144", o.getTelephone());
		assertEquals(lastName, o.getLastName());
	}

	@Test
	void testFindByLastNameNonExistent() {
		String lastName = "Unexisting";
		Owner o = new Owner();
		o.setAddress("25 Bonjour");
		o.setCity("Montreal");
		o.setFirstName("Tamoulou");
		o.setLastName("Ok");
		o.setTelephone("5145145144");
		ownerRepository.save(o);
		o = null;

		List<Owner> list;
		if ((List<Owner>) ownerRepository.findByLastName(lastName) instanceof List)
			list = (List<Owner>) ownerRepository.findByLastName(lastName);
		else
			list = new ArrayList(ownerRepository.findByLastName(lastName));

		assertEquals(list.size(), 0);

	}

	@Test
	void testFindByLastNameNull() {

		String lastName = null;
		Owner o = new Owner();
		o.setAddress("25 salut");
		o.setTelephone("5145145144");
		o.setCity("Montreal");
		o.setFirstName("Bonjour");
		o.setLastName(lastName);

		o = null;

		try {
			ownerRepository.save(o);
			fail();
		}
		catch (Exception e)

		{
			assertEquals(
					"Entity must not be null.; nested exception is java.lang.IllegalArgumentException: Entity must not be null.",
					e.getMessage());
			List<Owner> list;
			if ((List<Owner>) ownerRepository.findByLastName(lastName) instanceof List)
				list = (List<Owner>) ownerRepository.findByLastName(lastName);
			else
				list = new ArrayList(ownerRepository.findByLastName(lastName));

			assertEquals(list.size(), 0);

		}
	}

	@Test
	void testFindByIdExistent() {

		String lastName = "Working";
		int id = 13;
		Owner o = new Owner();
		o.setAddress("25 salut");
		o.setTelephone("5145145144");
		o.setCity("Montreal");
		o.setFirstName("Bonjour");
		o.setLastName(lastName);
		System.out.println(o.getId());
		o.setId(id);

		ownerRepository.save(o);

		o = null;

		o = ownerRepository.findById(id);

		assertNotNull(o);
		assertEquals("Bonjour", o.getFirstName());
		assertEquals("Montreal", o.getCity());
		assertEquals("25 salut", o.getAddress());
		assertEquals("5145145144", o.getTelephone());
		assertEquals(lastName, o.getLastName());

	}

	@Test
	void testFindByIdNonExistent() {
		// TODO Zhanna
		Owner owner = ownerRepository.findById(100);
		assertNull(owner);
	}

	@Test
	void testFindByIdNull() {
		// TODO Zhanna
		Owner owner = ownerRepository.findById(null);
		assertNull(owner);
	}

	@Test
	void testSaveValidOwner() {
		// TODO Zhanna
		Owner georgia = new Owner();
		georgia.setFirstName("Georgia");
		georgia.setLastName("O'Keeffe");
		georgia.setAddress("21120 U.S. 84 Abiquiú");
		georgia.setCity("New Mexico");
		georgia.setTelephone("5059000987");
		ownerRepository.save(georgia);
		assertThat(georgia.getId()).isGreaterThan(0);
		assertThat(georgia.getId()).isEqualTo(14); // B/c already 10 owners in database

	}

	@Test
	void testSaveNull() {
		// TODO Zhanna
		Owner nullOwner = null;
		assertThrows(Exception.class, () -> ownerRepository.save(nullOwner));
	}

}
