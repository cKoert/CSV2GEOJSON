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
	String propSep;
	String propXField;
	String propYField;

	// constructor
	public CsvReader(String propSep, String propXField, String propYField) {
		super();
		this.propSep = propSep;
		this.propXField = propXField;
		this.propYField = propYField;
	}

	// Read CSV and return ArrayList
	public ArrayList<CsvPoint> readCSV(InputStream inStream, List<String> relFields) throws IOException {

		InputStreamReader inStreamR = new InputStreamReader(inStream); // read content
		BufferedReader buffR = new BufferedReader(inStreamR);

		ArrayList<CsvPoint> pointList = new ArrayList<CsvPoint>();
		// to make loop work
		String line = ""; // empty starting value

		line = buffR.readLine();
		String[] columns = line.split(propSep); // all columns
		List relIndices = new ArrayList();

		for (int k = 0; k < columns.length; k++) {
			if (relFields.contains(columns[k])) { // test if it is a relevant field - comparison with usedFields
				relIndices.add(k);
			}
		}

		while ((line = buffR.readLine()) != null) {
			HashMap<String, String> pointsAtt = new HashMap<String, String>();
			String[] featureContent = line.split(propSep, -1); // -1 last element could be null
			CsvPoint point = new CsvPoint();

			for (int i = 0; i < columns.length; i++) {
				if(relIndices.contains(i)) { 
					pointsAtt.put(columns[i], featureContent[i]);
				}
				if (columns[i].equals(this.propXField)) { // compare column with xField from properties
					point.setLongitude(featureContent[i]);
				}
				if (columns[i].equals(this.propYField)) { // // compare column with yField from properties
					point.setLatitude(featureContent[i]);
				}

			}
			point.setPoints(pointsAtt);
			pointList.add(point);
		}

		return pointList;
	}
}