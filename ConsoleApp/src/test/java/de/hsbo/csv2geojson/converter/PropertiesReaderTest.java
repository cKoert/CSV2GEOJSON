package de.hsbo.csv2geojson.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PropertiesReaderTest {

	private static PropertiesReader propReader;

	@BeforeAll
	static void setup() {
		propReader = new PropertiesReader();
	}
	
	@DisplayName("Test content of attributes")
	@Test
	void testAttributeContents() throws Exception {
		String path = "de/hsbo/csv2geojson/converter/properties-test.properties";
		propReader.readProperties(getClass().getClassLoader().getResource(path).toURI().getPath());
		Assertions.assertEquals("https://github.com/timkuro/CSV2GEOJSON", propReader.getPropUrl());
		Assertions.assertEquals(",", propReader.getPropSep());
		Assertions.assertEquals("Longitude", propReader.getPropXField());
		Assertions.assertEquals("Latitude", propReader.getPropYField());
		Assertions.assertEquals(".\\stadiums-with-GPS-coordinates.json", propReader.getPropTarget());
		List<String> relFields = Arrays.asList("Field1", "Field2", "Field3");
		Assertions.assertEquals(relFields, propReader.getRelFields());
	
	}
	@DisplayName("Test incorrect path")
	@Test
	void testIncorrectPath() throws Exception {
		String path = "wrong/path";
		IOException e = new IOException();
		Assertions.assertThrows(e.getClass(), () ->{
			propReader.readProperties(path);
		});
		
	}

	
	}
