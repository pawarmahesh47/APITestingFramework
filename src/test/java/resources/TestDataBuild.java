package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String language) {
		AddPlace add = new AddPlace();
		add.setAccuracy(50);
		add.setLanguage(language);
		add.setName(name);
		add.setPhone_number("(+91) 983 893 3937");
		add.setTypes(null);
		add.setWebsite("http://google.com");

		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);

		add.setLocation(l);

		List<String> list = new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");
		
		return add;
	}
	
	public String deletePlaceAPIPayload(String place_id)
	{
		return "{\r\n    \"place_id\":\""+place_id+"\"\r\n}";
	}

}
