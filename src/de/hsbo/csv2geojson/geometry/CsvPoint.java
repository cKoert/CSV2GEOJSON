package de.hsbo.csv2geojson.geometry;

import java.util.HashMap;


public class CsvPoint{
	
	HashMap<String, String> points = new HashMap<String, String>();
	
	public CsvPoint(HashMap<String, String> points) {
		this.points = points;
	}
	
	public String getLatitude(String keyLat) {
		return points.get(keyLat);
	}

	public String getLongitude(String keyLong) {
		return points.get(keyLong);
	}

	public HashMap<String, String> getPoints() {
		return points;
	}

	
	
	
	

	

}
