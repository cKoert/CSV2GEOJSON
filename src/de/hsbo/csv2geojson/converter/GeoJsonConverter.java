package de.hsbo.csv2geojson.converter;

import java.util.ArrayList;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import de.hsbo.csv2geojson.geometry.CsvPoint;

/**
 * This class converts the ArrayList to a GeoJSON-file
 * constructor uses points with coordinate describing column names
 * using GeoJSON-syntax
 */

public class GeoJsonConverter {

	// attributes
	ArrayList<CsvPoint> points;
	String xField;
	String yField;

	// constructor
	public GeoJsonConverter(ArrayList<CsvPoint> points, String xField, String yField) {
		super();
		this.points = points;
		this.xField = xField;
		this.yField = yField;
	}

	// convert
	public String createGEOJSON() throws Exception {
		try {
			ObjectMapper mapper = new ObjectMapper();

			ObjectNode collectionNode = mapper.createObjectNode();
			ArrayNode features = mapper.createArrayNode();

			for (int i = 0; i < points.size(); i++) {
				// create GeoJSON nodes
				ObjectNode feature1 = mapper.createObjectNode();
				ObjectNode properties1 = mapper.createObjectNode();
				ObjectNode geometry = mapper.createObjectNode();

				feature1.put("type", "Feature");
				// fill geometry node
				Double[] coord = new Double[2];
				coord[0] = Double.parseDouble(points.get(i).getLongitude(xField));
				coord[1] = Double.parseDouble(points.get(i).getLatitude(yField));
				geometry.put("type", "Point");
				geometry.putPOJO("coordinates", coord);

				// create node
				ObjectNode properties = mapper.valueToTree(points.get(i).getPoints());

				// add properties and geometry to feature node
				feature1.putPOJO("properties", properties);
				feature1.putPOJO("geometry", geometry);

				features.add(feature1);
			}
			collectionNode.put("type", "FeatureCollection");
			collectionNode.putPOJO("features", features);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(collectionNode);

		} catch (JsonProcessingException e) {
			throw e;
		}

	}

}
