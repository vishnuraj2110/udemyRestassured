package Resourses;

public enum APIEndpoint {
	
	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePLaceAPI("/maps/api/place/delete/json");
	
	private String resource;
	
	APIEndpoint(String resource) {
		this.resource=resource;
	}
	public String getResource() {
		return resource;
	}

}
