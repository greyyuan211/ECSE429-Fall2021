package org.springframework.samples.petclinic.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.samples.petclinic.model.Visit;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class VisitRepositoryTests {

	@Autowired
	private VisitRepository visitRepository;

	@Test
	void testSaveVisitValid() {
		// TODO Mohamed
	}

	@Test
	void testSaveVisitNull() {
		// TODO Linwei
		// Exception exception = assertThrows(NullPointerException.class, () -> {
		// visitRepository.save(null);
		// });
		assertThrows(Exception.class, () -> visitRepository.save(null));
	}

	@Test
	void testFindPetByExistentId() {
		// TODO Linwei
		List<Visit> petId7_visit = visitRepository.findByPetId(7);
		assertThat(petId7_visit.get(0).getId()).isEqualTo(1);
		assertThat(petId7_visit.get(0).getPetId()).isEqualTo(7);
		assertThat(petId7_visit.get(0).getDate()).isEqualTo("2013-01-01");
		assertThat(petId7_visit.get(0).getDescription()).isEqualTo("rabies shot");

		assertThat(petId7_visit.get(1).getId()).isEqualTo(4);
		assertThat(petId7_visit.get(1).getPetId()).isEqualTo(7);
		assertThat(petId7_visit.get(1).getDate()).isEqualTo("2013-01-04");
		assertThat(petId7_visit.get(1).getDescription()).isEqualTo("spayed");

		List<Visit> petId8_visit = visitRepository.findByPetId(8);
		assertThat(petId8_visit.get(0).getId()).isEqualTo(2);
		assertThat(petId8_visit.get(0).getPetId()).isEqualTo(8);
		assertThat(petId8_visit.get(0).getDate()).isEqualTo("2013-01-02");
		assertThat(petId8_visit.get(0).getDescription()).isEqualTo("rabies shot");

		assertThat(petId8_visit.get(1).getId()).isEqualTo(3);
		assertThat(petId8_visit.get(1).getPetId()).isEqualTo(8);
		assertThat(petId8_visit.get(1).getDate()).isEqualTo("2013-01-03");
		assertThat(petId8_visit.get(1).getDescription()).isEqualTo("neutered");

	}

	@Test
	void testFindPetByNonexistentId() {
		// TODO Linwei
		List<Visit> nonExist = visitRepository.findByPetId(40);
		assertThat(nonExist.size()).isEqualTo(0);
	}

	@Test
	void testFindPetNull() {
		// TODO Linwei
		List<Visit> nullVisit = visitRepository.findByPetId(null); // will return an empty
																	// list
		assertThat(nullVisit.size()).isEqualTo(0);
	}

}
