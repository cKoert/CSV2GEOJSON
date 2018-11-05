package de.hsbo.csv2geojson.converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * This class reads the single properties of the properties-file. read the
 * properties file with a BufferedReader use java util class "properties" to
 * create an object read the object with given methods
 */

public class PropertiesReader {

	// attributes
	private String propUrl; // url
	private String propSep; // field separator
	private String propXField; // longitude
	private String propYField; // latitude
	private String propTarget; // target location
	private String relFields; // columns to be transferred
	private String[] sepFields; // origin columns

	/*
	 * constructor to create a propertiesReader-object
	 */
	public PropertiesReader(String propertiesSpeicher) {
		// read
		Properties properties = new Properties();
		try {
			File fileIn = new File(propertiesSpeicher);
			BufferedReader reader = new BufferedReader(new FileReader(fileIn));
			properties.load(reader);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * assign contents to attributes
		 */
		this.propUrl = properties.getProperty("url");
		this.propSep = properties.getProperty("fieldSep");
		this.propXField = properties.getProperty("xField");
		this.propYField = properties.getProperty("yField");
		this.propTarget = properties.getProperty("dest"); 			// To read the single '\' there must be an odd number of '\'s in the .properties file
		this.relFields = properties.getProperty("relevantFields");	// relevant columns
		this.sepFields = relFields.split(",");						// all columns
	}

	/*
	 * getter and setter methods
	 */
	
	public String getPropUrl() {
		return propUrl;
	}

	public void setPropUrl(String propUrl) {
		this.propUrl = propUrl;
	}

	public String getPropSep() {
		return propSep;
	}

	public void setPropSep(String propSep) {
		this.propSep = propSep;
	}

	public String getPropXField() {
		return propXField;
	}

	public void setPropXField(String propXField) {
		this.propXField = propXField;
	}

	public String getPropYField() {
		return propYField;
	}

	public void setPropYField(String propYField) {
		this.propYField = propYField;
	}

	public String getPropDest() {
		return propTarget;
	}

	public void setPropDest(String propZiel) {
		this.propTarget = propZiel;
	}

	public String[] getSepFields() {
		return sepFields;
	}

	public void setSepFields(String[] sepFields) {
		this.sepFields = sepFields;
	}

}
