package de.hsbo.csv2geojson.converter;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.hsbo.csv2geojson.geometry.CsvPoint;

class GeoJsonConverterTest {

	private static GeoJsonConverter geojson;
	@BeforeAll
	static void setup() {
		CsvPoint point = new CsvPoint();
		point.setLatitude("52.1234");
		point.setLongitude("7.1234");
		HashMap<String, String> attributes = new HashMap();
		attributes.put("attribute1", "attribute value");
		attributes.put("attribute2", "example");
		point.setAttributes(attributes);
		ArrayList<CsvPoint> pointList = new ArrayList();
		pointList.add(point);
		geojson = new GeoJsonConverter(pointList);
	}
	
	@DisplayName("Test pointlist")
	@Test
	void testPointList() throws Exception{
		ArrayList<CsvPoint> pointList = geojson.getPointList();
		
		Assertions.assertEquals("52.1234", pointList.get(0).getLatitude());
		Assertions.assertEquals("7.1234", pointList.get(0).getLongitude());
		Assertions.assertTrue(pointList.get(0).getAttributes().containsKey("attribute1"));
		Assertions.assertTrue(pointList.get(0).getAttributes().containsValue("attribute value"));
		Assertions.assertTrue(pointList.get(0).getAttributes().containsKey("attribute2"));
		Assertions.assertTrue(pointList.get(0).getAttributes().containsValue("example"));
	}
	
	@DisplayName("Test geojson String")
	@Test
	void testJsonString() throws Exception {
		String json = geojson.createGEOJSON();
		String path = "de/hsbo/csv2geojson/converter/json-test";
		String url = getClass().getClassLoader().getResource(path).toURI().getPath();
		File file = new File(url);
		FileInputStream stream = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		stream.read(data);
		stream.close();
		String testString = new String(data, "UTF-8");
		Assertions.assertEquals(testString, json);
	}

}
