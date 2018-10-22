package Experimente2;

import java.util.Arrays;

public class CSV {
	
	String[] felder;
	private static int stelleXFeld = 5;
	private static int stelleYFeld = 6;

	public CSV(int anzahl) {
		super();
		this.felder = new String[anzahl];
	}
	
	public void setStelleKooridnaten(int stelleXFeld, int stelleYFeld) {
		stelleXFeld = 6;
		stelleYFeld = 5;
	}
	
	
	public void setAttribut(int index, String wert) {
		felder[index] = wert;
	}
	
	public String getLongitude() {
		return felder[stelleXFeld];
	}
	public String getLatitude() {
		return felder[stelleYFeld];
	}
	
	public String getStelleKooridnaten() {
		return "X: " + stelleXFeld + " Y: " + stelleYFeld;
	}


}
