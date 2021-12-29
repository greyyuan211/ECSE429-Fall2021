package features;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.samples.petclinic.PetClinicApplication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.http.HttpHeaders;

//@ContextConfiguration
// @SpringBootTest(classes = PetClinicApplication.class, webEnvironment =
// SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CucumberConfig {

	@LocalServerPort
	public int port;

	public final String staticURL = "http://localhost:" + 8080;

	public HttpHeaders httpHeaders;

	@Before
	public void setUp() throws Exception {
		httpHeaders = new HttpHeaders();
	}

}
