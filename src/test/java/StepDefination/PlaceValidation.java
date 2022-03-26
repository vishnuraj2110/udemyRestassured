package StepDefination;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import Resourses.APIEndpoint;
import Resourses.TestDataBuild;
import Resourses.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class PlaceValidation extends Utils {

	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	public static String place_id;
	TestDataBuild data = new TestDataBuild();

	@Given("Add place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {

		res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));

	}

	@When("user calls {string} With {string} http Request")
	public void user_calls_with_http_request(String apiName, String httpMethod) {

		APIEndpoint resorces = APIEndpoint.valueOf(apiName);
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (httpMethod.equalsIgnoreCase("post")) {
			response = res.when().post(resorces.getResource());
		} else if (httpMethod.equalsIgnoreCase("get")) {
			response = res.when().get(resorces.getResource());
		} else if (httpMethod.equalsIgnoreCase("delete")) {
			response = res.when().delete(resorces.getResource());
		}
	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(int expSatusCode) {
		assertEquals(response.getStatusCode(), expSatusCode);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expvalue) {
		
		assertEquals(getJsonPath(response, keyValue), Expvalue);
	}
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expName, String apiName) throws IOException {
		place_id = getJsonPath(response,"place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
	    user_calls_with_http_request(apiName, "Get");
	    String actName = getJsonPath(response,"name");
	    assertEquals(actName,expName);
	}
	
	@Given("DeletePlace PayLoad")
	public void delete_place_pay_load() throws IOException {
	   res= given().spec(requestSpecification()).body(data.deletePlacePayLoad(place_id));
	}


}
