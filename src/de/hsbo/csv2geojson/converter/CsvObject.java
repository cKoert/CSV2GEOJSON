package de.hsbo.csv2geojson.converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import de.hsbo.csv2geojson.geometry.Point;
import de.hsbo.csv2geojson.geometry.Stadion;

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
		
	//Read CSV and return ArrayList
	public ArrayList<Point> readCSV(String[] relFields) throws Exception {
		try {
			ArrayList<Point> points = new ArrayList<Point>();
			// Damit Schleife funktioniert
			String line = ""; // Leerer Startwert
			boolean ersteZeile = true;
			Boolean[] usedFields = new Boolean[8];
			while ((line = buffR.readLine()) != null) {
				HashMap<String, String> pointsAtt = new HashMap<String, String>();
				String[] pointAr = line.split(propSep);
				//read first line
				if (ersteZeile == true) {
					for(int i = 0,  k = 0; i<pointAr.length; i++, k++) {
						if(pointAr[i].equals(relFields[k])) {
							usedFields[i] = true;
							 //test if it is a relevant field - comparison with .properties
						}
						else{
							usedFields[i] = false;
							k--;
						}
					}
					ersteZeile = false;
				//reading the objects
				} else if (ersteZeile == false){
					for(int i = 0, k = 0; i < pointAr.length; i++, k++)
						if(usedFields[i] == true) {		//test if it is a relevant field - comparison with usedFields
							pointsAtt.put(relFields[k], pointAr[i]);
							
						}
						else {
							k--;
						}
					Point point = new Point(pointsAtt);
					points.add(point);
							
				}
				
			}
			return points;
		} catch (IOException e) {
			throw e;
		}
		
	}




}
