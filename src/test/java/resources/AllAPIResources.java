package resources;

public enum AllAPIResources {
	
	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");

	String resource;
	
	AllAPIResources(String resource) {
		
		this.resource=resource;
		
	}
	
	public String getResouce()
	{
		return resource;
	}

}
