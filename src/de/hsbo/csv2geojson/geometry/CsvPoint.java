package de.hsbo.csv2geojson.geometry;

import java.util.HashMap;

public class CsvPoint {

	private String Longitude;
	private String Latitude;

	HashMap<String, String> points = new HashMap<String, String>();

	public CsvPoint() {		// standard constructor
	}

	public String getLongitude() {
		return Longitude;
	}

	public String getLatitude() {
		return Latitude;
	}

	public HashMap<String, String> getPoints() {
		return points;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public void setPoints(HashMap<String, String> points) {
		this.points = points;
	}

}
