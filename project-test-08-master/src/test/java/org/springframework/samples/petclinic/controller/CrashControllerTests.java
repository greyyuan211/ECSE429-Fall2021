package org.springframework.samples.petclinic.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.assertj.core.api.Assertions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(CrashController.class)
public class CrashControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetOups() throws Exception {
		// TODO Zhanna
		String exceptionMessage = "Expected: controller used to showcase what " + "happens when an exception is thrown";

		Assertions.assertThatThrownBy(
				() -> mockMvc.perform(MockMvcRequestBuilders.get("/oups").contentType(MediaType.APPLICATION_JSON)))
				.hasCauseInstanceOf(RuntimeException.class).hasMessageContaining(exceptionMessage);
	}

}
