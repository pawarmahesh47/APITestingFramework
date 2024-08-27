package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		StepDefination s = new StepDefination();

		if (StepDefination.place_id == null) {
			s.add_place_payload("Nirvi", "Wai");
			s.user_calls_with_post_http_request("addPlaceAPI", "POST");
			s.verify_place_id_created_map_to("Nirvi", "getPlaceAPI");
		}

	}

}
