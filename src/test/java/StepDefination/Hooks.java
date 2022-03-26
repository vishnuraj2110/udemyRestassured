package StepDefination;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	PlaceValidation place = new PlaceValidation();
	
	
	@Before("@deletePlace")
	public void beforeScenario() throws IOException {
		if(PlaceValidation.place_id==null) {
		place.add_place_payload_with("vishnu", "french", "Asia");
		place.user_calls_with_http_request("addPlaceAPI","POST");
		place.verify_place_id_created_maps_to_using("vishnu", "getPlaceAPI");
		}
	}

}
