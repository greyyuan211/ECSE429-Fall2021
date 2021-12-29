package org.springframework.samples.petclinic.model;

import org.junit.jupiter.api.Test;
import org.springframework.util.SerializationUtils;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class VetsTests {

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
	void testGetVetList() {
		// TODO Linwei
		testVets = new Vets();
		List<Vet> vetList = new ArrayList<>();
		assertThat(testVets.getVetList()).isEqualTo(vetList);
	}

}
