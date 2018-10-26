package de.hsbo.csv2geojson.converter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URI;

import javax.swing.JFileChooser;

public class GeoJsonFile implements Serializable{
	
	private String jsonString;
	private String propUrl;

	public GeoJsonFile(String jsonString, String propUrl) {
		super();
		this.jsonString = jsonString;
		this.propUrl = propUrl;
	}
	
	
	public void createFile() throws Exception{
		try {
		File fileOut = new File(propUrl);
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileOut));
		out.writeObject(jsonString);
		out.close();
		}catch(IOException e) {
			throw e;
		}

	}

}
