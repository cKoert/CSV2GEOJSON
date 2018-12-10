package de.hsbo.csv2geojson.geometry;

import java.util.HashMap;

public class CsvPoint {

	private String Longitude;
	private String Latitude;

	HashMap<String, String> attributes = new HashMap<String, String>();

	public CsvPoint() {		// standard constructor
	}

	public String getLongitude() {
		return Longitude;
	}

	public String getLatitude() {
		return Latitude;
	}

	public HashMap<String, String> getAttributes() {
		return attributes;
	}

	public void setLongitude(String longitude) {
		if(longitude.contains(","))
			longitude.replace(',', '.');
		Longitude = longitude;
	}

	public void setLatitude(String latitude) {
		if(latitude.contains(","))
			latitude.replace(',', '.');
		Latitude = latitude;
	}

	public void setAttributes(HashMap<String, String> attributes) {
		this.attributes = attributes;
	}

}
