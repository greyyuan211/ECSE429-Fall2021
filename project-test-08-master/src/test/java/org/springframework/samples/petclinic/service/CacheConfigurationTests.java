package org.springframework.samples.petclinic.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CacheConfigurationTests {

	@Test
	void testPetClinicCacheConfigurationCustomizer() {
		// TODO Yacine
		CacheConfiguration cacheConfiguration = new CacheConfiguration();
		assertNotNull(cacheConfiguration.petclinicCacheConfigurationCustomizer());
	}

}
