/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsbo.csv2geojson.converter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.hsbo.csv2geojson.geometry.CsvPoint;

/**
 *
 * @author <a href="mailto:s.drost@52north.org">Sebastian Drost</a>
 */
public class CsvReaderTest {

	private static CsvReader reader;

	@BeforeAll
	static void setup() {
		reader = new CsvReader(";", "lat", "lon");
	}

	@DisplayName("Tests reading CSV file with appropriate values")
	@Test
	void testReadAppropriateCsv() throws Exception {
		List relevantFields = Arrays.asList("name", "description");
		InputStream in = getClass().getResourceAsStream("csv-test");
		List points = reader.readCSV(in, relevantFields);

		Assertions.assertEquals(2, points.size());
	}

	@DisplayName("Tets coordinate fields")
	@Test
	void testCoordFields() throws Exception {
		List relevantFields = Arrays.asList("name", "description");
		InputStream in = getClass().getResourceAsStream("csv-test");
		List<CsvPoint> points = reader.readCSV(in, relevantFields);

		String[] realCoordinates = { "7.1234", "52.1234" };
		String[] readCoordinates = { points.get(0).getLatitude(), points.get(0).getLongitude() };
		// readCoordinates[0].isEmpty();
		// readCoordinates[1].isEmpty();
		Assertions.assertNotNull(readCoordinates[0]); // necessary?
		Assertions.assertNotNull(readCoordinates[1]);
		Assertions.assertArrayEquals(realCoordinates, readCoordinates);
	}

	@DisplayName("Test number of attributes")
	@Test
	void testNoOfAtt() throws Exception {
		List relevantFields = Arrays.asList("name", "description");
		InputStream in = getClass().getResourceAsStream("csv-test");
		List<CsvPoint> points = reader.readCSV(in, relevantFields);

		Assertions.assertEquals(2, points.get(0).getAttributes().size());
		Assertions.assertEquals("test1", points.get(0).getAttributes().get("name"));
	}

	@DisplayName("Test incorrect input values")
	@Test
	void testIncorrectInputValues() throws Exception {
		List relevantFields = Arrays.asList("name", "description");
		InputStream in = getClass().getResourceAsStream("csv-test-exception");
		// List<CsvReader> points = reader.readCSV(in, relevantFields);
		Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			reader.readCSV(in, relevantFields);
		});
	}
}
