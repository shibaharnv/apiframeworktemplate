package stepdefinition;

import static org.junit.Assert.assertEquals;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import Resources.APIResources;
import Resources.TestDataBuild;
import Resources.Utils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefinition extends Utils {
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	static String place_id;
	
	TestDataBuild data= new TestDataBuild();

	APIResources resourceAPI;
	@Given("This is testing method")
	public void this_is_testing_method() {
		System.out.println("sample check");
	}

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		res=given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
	}
	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		//constructor will be called with value of resource you pass
		 resourceAPI=APIResources.valueOf(resource);


		if(method.equalsIgnoreCase("POST"))
			response=res.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
			response=res.when().get(resourceAPI.getResource());
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String expectedvalue) {
		assertEquals(getJsonPath(response, keyvalue),expectedvalue);
	}
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedname, String resource ) throws IOException {

		place_id=getJsonPath(response, "place_id");
		res=given().spec(requestSpecification()).queryParam("place_id",place_id);
		user_calls_with_http_request(resource,"GET");

		String actualname=getJsonPath(response, "name");
		assertEquals(actualname,expectedname);
	}


}
