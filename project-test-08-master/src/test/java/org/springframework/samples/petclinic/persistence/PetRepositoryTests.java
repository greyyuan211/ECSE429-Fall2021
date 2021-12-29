package org.springframework.samples.petclinic.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Collection;

@ExtendWith(SpringExtension.class)
// this annotation will only scan @Entity and SpringDataJPARepositories b/c only testing
// repository layer
@DataJpaTest
public class PetRepositoryTests {

	@Autowired
	private PetRepository petRepository;

	@Test
	void testFindPetTypes() {
		// TODO Zhanna
		Collection<PetType> petTypes = petRepository.findPetTypes();
		assertThat(petTypes.size()).isGreaterThan(0);
		for (PetType petType : petTypes) {
			switch (petType.getName()) {
			case "bird":
				assertThat(petType.getName()).isEqualTo("bird");
				break;
			case "cat":
				assertThat(petType.getName()).isEqualTo("cat");
				break;
			case "dog":
				assertThat(petType.getName()).isEqualTo("dog");
				break;
			case "hamster":
				assertThat(petType.getName()).isEqualTo("hamster");
				break;
			case "lizard":
				assertThat(petType.getName()).isEqualTo("lizard");
				break;
			case "snake":
				assertThat(petType.getName()).isEqualTo("snake");
				break;
			}
		}
	}

	@Test
	void testFindByIdExistentId() {
		// TODO Zhanna
		Pet petId1_Leo = petRepository.findById(1);
		assertThat(petId1_Leo.getId()).isEqualTo(1);
		assertThat(petId1_Leo.getName()).startsWith("Leo");
		assertThat(petId1_Leo.getOwner().getFirstName()).isEqualTo("George");
		assertThat(petId1_Leo.getBirthDate()).isEqualTo("2010-09-07");

		Pet petId7_Samantha = petRepository.findById(7);
		assertThat(petId7_Samantha.getId()).isEqualTo(7);
		assertThat(petId7_Samantha.getName()).startsWith("Samantha");
		assertThat(petId7_Samantha.getOwner().getFirstName()).isEqualTo("Jean");
		assertThat(petId7_Samantha.getBirthDate()).isEqualTo("2012-09-04");

		Pet petId8_Max = petRepository.findById(8);
		assertThat(petId8_Max.getId()).isEqualTo(8);
		assertThat(petId8_Max.getName()).startsWith("Max");
		assertThat(petId8_Max.getOwner().getFirstName()).isEqualTo("Jean");
		assertThat(petId8_Max.getBirthDate()).isEqualTo("2012-09-04");
	}

	@Test
	void testFindByIdNonexistentId() {
		// TODO Ruixin
		Pet nonExist = petRepository.findById(40);
		assertNull(nonExist);
	}

	@Test
	void testFindByIdNull() {
		// TODO Ruixin
		assertThrows(Exception.class, () -> petRepository.findById(null));
	}

	@Test
	void testSaveValidPet() {
		// TODO Ruixin
		Owner owner = new Owner();
		owner.setId(1);
		// assertEquals(owner,ownerRepository.findById(1));
		PetType petType = new PetType();
		petType.setId(1);
		petType.setName("cat");
		Pet newPet = new Pet();
		newPet.setName("aria");
		LocalDate date = LocalDate.parse("2021-09-07");
		newPet.setBirthDate(date);
		newPet.setOwner(owner);
		newPet.setType(petType);
		petRepository.save(newPet);
		assertThat(newPet.getId()).isGreaterThan(0);
		assertThat(newPet.getId()).isEqualTo(14); // B/c already 10 owners in database

		// assertEquals(newPet,petRepository.findById(50));

	}

	@Test
	void testSaveNull() {
		// TODO Mohamed
		try {
			petRepository.save(null);
		}
		catch (Exception e) {
			assertEquals(e.getMessage(),
					"Entity must not be null.; nested exception is java.lang.IllegalArgumentException: Entity must not be null.");
		}

	}

}
