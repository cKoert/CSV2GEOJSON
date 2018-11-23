package de.hsbo.csv2geojson.converter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class GeoJsonFileWriter implements Serializable {

	private String jsonString;
	private String propUrl;

	public GeoJsonFileWriter(String jsonString, String propUrl) {
		super();
		this.jsonString = jsonString;
		this.propUrl = propUrl;
	}

	public void createFile() throws IOException {
		File fileOut = new File(propUrl);
		BufferedWriter out = new BufferedWriter(new FileWriter(fileOut));
		out.write(jsonString);
		out.close();

	}

}
