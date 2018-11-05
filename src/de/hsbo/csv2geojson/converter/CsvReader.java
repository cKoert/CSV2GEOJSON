package de.hsbo.csv2geojson.converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import de.hsbo.csv2geojson.geometry.CsvPoint;

/**
 * This class reads the BufferedReader (csv-file)
 * each line is converted to a point object (only relevant fields)
 * points are added to a ArrayList
 */

public class CsvReader {

	// attributes
	BufferedReader buffR;
	String propSep;
	String propXField;
	String propYField;

	// constructor
	public CsvReader(BufferedReader buffR, String propSep, String propXField, String propYField) {
		super();
		this.buffR = buffR;
		this.propSep = propSep;
		this.propXField = propXField;
		this.propYField = propYField;
	}

	// Read CSV and return ArrayList
	public ArrayList<CsvPoint> readCSV(String[] relFields) throws Exception {
		try {
			ArrayList<CsvPoint> points = new ArrayList<CsvPoint>();
			// to make loop work
			String line = ""; // empty starting value
			boolean ersteZeile = true;
			Boolean[] usedFields = new Boolean[8];
			while ((line = buffR.readLine()) != null) {
				HashMap<String, String> pointsAtt = new HashMap<String, String>();
				String[] pointAr = line.split(propSep);
				// read first line
				if (ersteZeile == true) {
					for (int i = 0, k = 0; i < pointAr.length; i++, k++) {
						if (pointAr[i].equals(relFields[k])) {
							usedFields[i] = true;
							// test if it is a relevant field - comparison with .properties
						} else {
							usedFields[i] = false;
							k--;
						}
					}
					ersteZeile = false;
					// reading the objects
				} else if (ersteZeile == false) {
					for (int i = 0, k = 0; i < pointAr.length; i++, k++)
						if (usedFields[i] == true) { // test if it is a relevant field - comparison with usedFields
							pointsAtt.put(relFields[k], pointAr[i]);

						} else {
							k--;
						}
					CsvPoint point = new CsvPoint(pointsAtt);
					points.add(point);

				}

			}
			return points;
		} catch (IOException e) {
			throw e;
		}

	}

}
