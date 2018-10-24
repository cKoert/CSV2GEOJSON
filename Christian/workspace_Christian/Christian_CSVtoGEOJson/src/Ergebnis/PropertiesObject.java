package Ergebnis;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesObject {
	
	// Attribute
	private String propUrl;
	private String propSep;
	private String propXField;
	private String propYField;
	private String propZiel;

	// Properties-Datei
	public PropertiesObject(String proptiesSpeicher) {
		// Einlesen
		Properties properties = new Properties();
		try {
			BufferedInputStream stream = new BufferedInputStream(new FileInputStream(proptiesSpeicher));
			properties.load(stream);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Inhalte der Properties-Datei zuweisen
		this.propUrl = properties.getProperty("url");
		this.propSep = properties.getProperty("fieldSep");
		this.propXField = properties.getProperty("xFeld");
		this.propYField = properties.getProperty("yFeld");
		this.propZiel = properties.getProperty("ort");
		this.propZiel.replaceAll("\\\\", "\\");
	}
	
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

	public String getPropZiel() {
		return propZiel;
	}

	public void setPropZiel(String propZiel) {
		this.propZiel = propZiel;
	}


}
