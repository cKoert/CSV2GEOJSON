package de.hsbo.csv2geojson.converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hsbo.csv2geojson.geometry.CsvPoint;

/**
 * This class reads the BufferedReader (csv-file) each line is converted to a
 * point object (only relevant fields) points are added to a ArrayList
 */

public class CsvReader {

	// attributes
	InputStream inStream;
	String propSep;
	String propXField;
	String propYField;

	// constructor
	public CsvReader(InputStream inStream, String propSep, String propXField, String propYField) {
		super();
		this.inStream = inStream;
		this.propSep = propSep;
		this.propXField = propXField;
		this.propYField = propYField;
	}

	// Read CSV and return ArrayList
	public ArrayList<CsvPoint> readCSV(List<String> relFields) throws IOException {

		InputStreamReader inStreamR = new InputStreamReader(inStream); // read content
		BufferedReader buffR = new BufferedReader(inStreamR);

		ArrayList<CsvPoint> points = new ArrayList<CsvPoint>();
		// to make loop work
		String line = ""; // empty starting value

		line = buffR.readLine();
		String[] columns = line.split(propSep); // all columns

		while ((line = buffR.readLine()) != null) {
			HashMap<String, String> pointsAtt = new HashMap<String, String>();
			String[] pointAr = line.split(propSep);

			for (int i = 0; i < columns.length; i++)
				if (relFields.contains(columns[i])) { // test if it is a relevant field - comparison with usedFields
					pointsAtt.put(columns[i], pointAr[i]);
				}
			CsvPoint point = new CsvPoint(pointsAtt);
			points.add(point);

		}
		return points;
	}

}
