package Ergebnis;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import CSVtoGeoJson.Stadion;

public class CsvObject {
	
	//Attribute
	BufferedReader buffR;
	String propSep;
	String propXField;
	String propYField;
	
	//Konstruktor
	public CsvObject(BufferedReader buffR, String propSep, String propXField, String propYField) {
		super();
		this.buffR = buffR;
		this.propSep = propSep;
		this.propXField = propXField;
		this.propYField = propYField;
	}
		
	
	public ArrayList<Stadion> readCSV() throws Exception {
		try {
			ArrayList<Stadion> stadien = new ArrayList<Stadion>();
			// Damit Schleife funktioniert
			String line = ""; // Leerer Startwert
			// Erste Zeile filtern
			String[] feldNamen; // Spaltenüberschriften
			boolean ersteZeile = true;
			while ((line = buffR.readLine()) != null) {
				String[] stadAr = line.split(propSep);
				if (ersteZeile == true) {
					feldNamen = stadAr;
					ersteZeile = false;
				} else {
					Stadion stadion = new Stadion(stadAr[0], stadAr[1], stadAr[2], stadAr[3], stadAr[4], stadAr[5],
							stadAr[6], stadAr[7]);
					stadien.add(stadion);
				}
			}
			return stadien;
		} catch (IOException e) {
			throw e;
		}
		
	}




}
