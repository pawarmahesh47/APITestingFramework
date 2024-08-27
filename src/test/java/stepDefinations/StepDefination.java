package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import pojo.AddPlace;
import pojo.Location;
import resources.AllAPIResources;
import resources.TestDataBuild;
import resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefination extends Utils {
	ResponseSpecification responceSpec;
	RequestSpecification request;
	Response response;
	TestDataBuild testdata = new TestDataBuild();
	static String place_id;

	@Given("Add place Payload {string} {string}")
	public void add_place_payload(String name, String language) throws IOException {

		
		AddPlace add = testdata.addPlacePayload(name, language);

		request = given().spec(requestSpec()).body(add);
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resourceName, String httpMethod) throws IOException {

		AllAPIResources resourcesAPI = AllAPIResources.valueOf(resourceName);
		System.out.println(resourcesAPI.getResouce());

		responceSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (httpMethod.equalsIgnoreCase("Post")) {
			response = request.when().post(resourcesAPI.getResouce());
		} else if (httpMethod.equalsIgnoreCase("get")) {
			response = request.when().get(resourcesAPI.getResouce());
		}
		else if (httpMethod.equalsIgnoreCase("delete")) {
			response = request.when().delete(resourcesAPI.getResouce());
		}
	}

	@Then("API call get success with status code {int}")
	public void api_call_get_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);

	}

	@Then("{string} response body is {string}")
	public void response_body_is(String keyValue, String expectedValue) {
		// Write code here that turns the phrase above into concrete actions
		System.out.println(getJsonPath(response, keyValue));
		assertEquals(getJsonPath(response, keyValue), expectedValue);
	}

	@Then("verify place_id created map to {string} using {string}")
	public void verify_place_id_created_map_to(String expectedName, String resourceName) throws IOException {

		place_id = getJsonPath(response, "place_id");
		request = given().spec(requestSpec()).queryParam("place_id", place_id);
		user_calls_with_post_http_request(resourceName, "Get");
		String actulaName = getJsonPath(response, "name");
		assertEquals(actulaName, expectedName);
		
	}
	
	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
		request = given().spec(requestSpec()).body(testdata.deletePlaceAPIPayload(place_id));
	    
	}

}
