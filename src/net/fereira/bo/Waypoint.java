package net.fereira.bo;

public class Waypoint {
    private String Lat;
    private String Long;
    private Geocache geocache;
    
	public Waypoint() {
		// TODO Auto-generated constructor stub
	}

	public String getLat() {
		return Lat;
	}

	public void setLat(String lat) {
		Lat = lat;
	}

	public String getLong() {
		return Long;
	}

	public void setLong(String l) {
		Long = l;
	}

	public Geocache getGeocache() {
		return geocache;
	}

	public void setGeocache(Geocache geocache) {
		this.geocache = geocache;
	}

}
