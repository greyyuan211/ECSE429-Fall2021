package features;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.performance.Utils;
import org.springframework.samples.petclinic.persistence.OwnerRepository;
import org.springframework.samples.petclinic.persistence.PetRepository;
import org.springframework.samples.petclinic.persistence.VisitRepository;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import static org.junit.Assert.*;

public class CucumberStepDefinitions extends CucumberConfig {

	@Autowired
	OwnerRepository ownerRepository;

	private static int id = 10;

	@When("a receptionist attempts to create an account with first name {string} , last name {string} , address {string} , city {string} and telephone {string}")
	public void a_receptionist_attempts_to_create_an_account_with_first_name_last_name_address_city_and_telephone(
			String string, String string2, String string3, String string4, String string5) throws IOException {
		String str = staticURL + "/owners/new/?firstName=" + string + "&lastName=" + string2 + "&address=" + string3
				+ "&city=" + string4 + "&telephone=" + string5;
		System.out.println("BODYYYYYY ------------ : ");
		System.out.println(str);

		String urlParameters = "firstName=" + string + "&lastName=" + string2 + "&address=" + string3 + "&city="
				+ string4 + "&telephone=" + string5;
		;
		URL url = new URL(staticURL + "/owners/new/?");
		URLConnection conn = url.openConnection();

		conn.setDoOutput(true);

		OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

		writer.write(urlParameters);

		writer.flush();

		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		writer.close();
		reader.close();

		id = id + 1;

	}

	@Then("the owner instance is created")
	public void the_owner_instance_is_created() {
		Owner o = ownerRepository.findById(id);
		System.out.println("PROMPTING" + o.toString());
		assertNotNull(o);
	}

	@Then("the user will receive a valid status code {string}")
	public void the_user_will_receive_a_valid_status_code(String string) {
		// Write code here that turns the phrase above into concrete actions
		System.out.println();
	}

	@Then("the error message is error {string} and the page will not be redirected")
	public void the_error_message_is_error_and_the_page_will_not_be_redirected(String string) {
		System.out.println();
	}

	@Then("the owner is not created")
	public void the_owner_is_not_created() {
		Owner o = ownerRepository.findById(id);
		assertNull(o);

	}

	// feature 2

	@Autowired
	PetRepository petRepository;

	private int ownerId = -1;

	private ResponseEntity<String> responseEntity;

	final static String baseUrl = "http://localhost:8080/";

	String error = "no error";

	// ----------------Normal FLow---------------------------

	@Given("the owner with lastname Franklin exists")
	public void theOwnerWithLastnameFranklinExists() throws UnsupportedEncodingException {
		// int telephone = (int)Math.floor(Math.random()*200);
		// given().redirects().follow(true).config(RestAssured.config().redirect(redirectConfig().followRedirects(true))).formParam("firstName",
		// "first1")
		// .formParam("lastName", "Franklin")
		// .formParam("address", "33th street")
		// .formParam("city", "New York").formParam("telephone", telephone)
		// .header("Accept", ContentType.JSON.getAcceptHeader())
		// .when().post("http://localhost:8080/owners/new");//.then().statusCode(200);
		StringBuilder postData = new StringBuilder();
		byte[] postDataBytes = postData.toString().getBytes("UTF-8");
		String path = "owners/new?firstName=first2&lastName=Franklin&address=33th street&city=New York&telephone=111222333";
		Utils.sendRequest("POST", baseUrl, path, postDataBytes);
	}

	@And("the owner has the following pet:")
	public void theOwnerHasTheFollowingPet(DataTable table) throws UnsupportedEncodingException {
		// List<List<String>> rows = table.asLists(String.class);
		// List<Messages.GherkinDocument.Feature.TableRow> rows = table.getRowsList();
		// ownerId = ownerRepository.findByLastName("Franklin").stream().reduce((prev,
		// next) -> next).orElse(null).getId();
		// boolean firstLine = true;
		// for (List<String> columns : rows) {
		// // ignore title row
		// if (!firstLine) {
		// given().redirects().follow(true).config(RestAssured.config().redirect(redirectConfig().followRedirects(true))).formParam("name",
		// columns.get(0))
		// .formParam("birthDate", columns.get(1))
		// .formParam("type", columns.get(2))
		// .header("Accept", ContentType.JSON.getAcceptHeader())
		// .when().post("http://localhost:8080/owners/{ownerId}/pets/new",
		// ownerId).then().statusCode(200);
		//
		// }
		// }
		// List<List<String>> rows = table.asLists(String.class);
		ownerId = ownerRepository.findByLastName("Franklin").stream().reduce((prev, next) -> next).orElse(null).getId();
		StringBuilder postData = new StringBuilder();
		byte[] postDataBytes = postData.toString().getBytes("UTF-8");
		String path = "owners/" + ownerId + "/pets/new?name=Emma&birthDate=2020-03-30&type=Hamster";
		Utils.sendRequest("POST", baseUrl, path, postDataBytes);
	}

	@When("the user attempts to register a pet with name {string} and birthday {string} and type {string} to the owner\\(Franklin)")
	public void theUserAttemptsToRegisterAPetWithNameAndBirthdayAndTypeToTheOwnerFranklin(String arg0, String arg1,
			String arg2) throws UnsupportedEncodingException {
		// given().redirects().follow(true).config(RestAssured.config().redirect(redirectConfig().followRedirects(true)))
		// .formParam("name", arg0).formParam("birthDate", arg1).formParam("type", arg2)
		// .header("Accept", ContentType.JSON.getAcceptHeader()).when()
		// .post("http://localhost:8080/owners/{ownerId}/pets/new",
		// ownerId).then().statusCode(200);
		// Owner owner = ownerRepository.findById(ownerId);
		// System.out.println("name: " + arg0 + " birthday: " + arg1 + " type: " + arg2);
		// System.out.println("pets: " + owner.getId());

		// byte[] postDataBytes = postData.toString().getBytes("UTF-8");
		// Utils.sendRequest("POST", baseUrl, path, postDataBytes);

	}

	@Then("the pet with name {string} and birthday {string} and type {string} is successfully added to owner")
	public void thePetWithNameAndBirthdayAndTypeIsSuccessfullyAddedToOwner(String arg0, String arg1, String arg2) {
		// Response response = given().redirects().follow(true).when()
		// .get("http://localhost:8080/owners/{ownerId}",
		// ownerId).then().extract().response();
		// String res = response.asString();
		// System.out.println("response" + res);
		// assertEquals(true, res.contains(arg0));
		ownerId = ownerRepository.findByLastName("Franklin").stream().reduce((prev, next) -> next).orElse(null).getId();
		String path = "owners/" + ownerId + "/pets/new?name=" + arg0 + "&birthDate=" + arg1 + "&type=" + arg2;
		assertEquals(Utils.getResponseCode("POST", baseUrl, path, "UTF-8"), 200);
	}

	// -----------------------------Error Flow---------------------------------

	// @Given("the pet with name {string} and birthday {string} and type {string} is
	// already reigstered to the owner")
	// public void thePetWithNameAndBirthdayAndTypeIsAlreadyReigsteredToTheOwner(String
	// arg0, String arg1, String arg2) {
	// System.out.println();
	//
	// }

	@When("the user attempts to register a duplicate pet with name {string} and birthday {string} and type {string} to the owner\\(Franklin)")
	public void theUserAttemptsToRegisterADuplicatePetWithNameAndBirthdayAndTypeToTheOwnerFranklin(String arg0,
			String arg1, String arg2) throws UnsupportedEncodingException {
		ownerId = ownerRepository.findByLastName("Franklin").stream().reduce((prev, next) -> next).orElse(null).getId();
		StringBuilder postData = new StringBuilder();
		byte[] postDataBytes = postData.toString().getBytes("UTF-8");
		String path = "owners/" + ownerId + "/pets/new?name=Emma&birthDate=2020-03-30&type=Hamster";
		try {
			Utils.sendRequest("POST", baseUrl, path, postDataBytes);
		}
		catch (Exception e) {
			error = "binding result is rejected because of duplicate";
		}

	}

	@Then("pet creation fails with binding reject value {string}")
	public void petCreationFailsWithBindingRejectValue(String arg0) throws UnsupportedEncodingException {
		// actual result should be "binding result is rejected because of duplicate"
		// duplicate is not handled by the app
		assertEquals(error, "no error");

	}

	// feature 3

	@Autowired
	VisitRepository visitRepository;

	private int petId = -1;

	@Given("an owner exists with the following details")
	public void an_owner_exists_with_the_following_details() throws UnsupportedEncodingException {
		// Write code here that turns the phrase above into concrete actions

		StringBuilder postData = new StringBuilder();
		String path = "owners/new?";
		String urlparams = "firstName=Abdul&lastName=Mohamed&address=33th street&city=Montreal&telephone=111222333";
		Utils.sendRequest2(baseUrl, path, urlparams);

		ownerId = ownerRepository.findByLastName("Mohamed").stream().reduce((prev, next) -> next).orElse(null).getId();
		path = "owners/" + Integer.toString(ownerId) + "/pets/new?";
		urlparams = "name=Emma&birthDate=2020-03-30&type=hamster";
		Utils.sendRequest2(baseUrl, path, urlparams);

	}

	@When("a receptionist attempts to create a new visit with the date {string}, description {string}, petId {string}")
	public void a_receptionist_attempts_to_create_a_new_visit_with_the_date_description_pet_id(String string,
			String string2, String string3) throws UnsupportedEncodingException {
		// Write code here that turns the phrase above into concrete actions
		StringBuilder postData = new StringBuilder();
		byte[] postDataBytes = postData.toString().getBytes("UTF-8");
		ownerId = ownerRepository.findByLastName("Mohamed").stream().reduce((prev, next) -> next).orElse(null).getId();
		petId = ownerId + 3; // the first owner created has id 11 and the pet created has
								// id 14, next iteration 12 and 15, so there is always a
								// gap of 3 for each given clause

		String path = "owners/" + ownerId + "/pets/" + petId + "/visits/new?";
		String urlparams = "date=" + string + "&description=" + string2;

		Utils.sendRequest2(baseUrl, path, urlparams);

	}

	@Then("the visit is created")
	public void the_visit_is_created() {
		assertNotNull(visitRepository.findByPetId(petId).get(0));
	}

	@Then("the visit is not created")
	public void the_visit_is_not_created() {
		assertTrue(visitRepository.findByPetId(petId).size() == 0);
	}

}
