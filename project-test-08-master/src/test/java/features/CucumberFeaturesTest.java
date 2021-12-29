package features;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.PetClinicApplication;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;

@RunWith(Cucumber.class)
@CucumberContextConfiguration
@SpringBootTest(classes = { PetClinicApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@CucumberOptions(plugin = { "pretty" }, dryRun = true, features = "src/test/resources/features", glue = "features")
public class CucumberFeaturesTest {

}
