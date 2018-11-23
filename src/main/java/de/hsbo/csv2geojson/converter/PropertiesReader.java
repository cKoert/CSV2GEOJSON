package de.hsbo.csv2geojson.converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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
	private List<String> relFields;

	/*
	 * constructor to create a propertiesReader-object
	 */
	public PropertiesReader() {

	}

	public void readProperties(String path) throws IOException {
		// read
		Properties properties = new Properties();
		File fileIn = new File(path);
		BufferedReader reader = new BufferedReader(new FileReader(fileIn));
		properties.load(reader);
		reader.close();

		/*
		 * assign contents to attributes
		 */
		this.propUrl = properties.getProperty("url");
		this.propSep = properties.getProperty("fieldSep");
		this.propXField = properties.getProperty("xField");
		this.propYField = properties.getProperty("yField");
		this.propTarget = properties.getProperty("target"); /*
															 * To read the single '\' there must be an odd number of
															 * '\'s in the .properties file
															 */
		String relFields = properties.getProperty("relevantFields"); // relevant columns
		this.relFields = Arrays.asList(relFields.split(",")); // all columns
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

	public String getPropTarget() {
		return propTarget;
	}

	public void setPropTarget(String propZiel) {
		this.propTarget = propZiel;
	}

	public List<String> getRelFields() {
		return relFields;
	}

	public void setrelFields(List<String> relFields) {
		this.relFields = relFields;
	}

}
