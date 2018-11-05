package de.hsbo.csv2geojson.converter;

import java.util.ArrayList;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import de.hsbo.csv2geojson.geometry.Point;


public class GeoJsonObject {

	// Attribute
	ArrayList<Point> points;
	String xField;
	String yField;

	// Konstrujtor
	public GeoJsonObject(ArrayList<Point> points, String xField, String yField) {
		super();
		this.points = points;
		this.xField = xField;
		this.yField = yField;
	}

	// Umwandlung in GeoJSON
	public String createGEOJSON() throws Exception {
		try {
			//Set<Point> points = points;
			ObjectMapper mapper = new ObjectMapper();

			ObjectNode collectionNode = mapper.createObjectNode();
			ArrayNode features = mapper.createArrayNode();

			for (int i = 0; i < points.size(); i++) {
				// GEOJson anlegen
				ObjectNode feature1 = mapper.createObjectNode();
				ObjectNode properties1 = mapper.createObjectNode();
				ObjectNode geometry = mapper.createObjectNode();

				feature1.put("type", "Feature");
				// geometry anlegen
				Double[] coord = new Double[2];
				coord[0] = Double.parseDouble(points.get(i).getLongitude(xField));
				coord[1] = Double.parseDouble(points.get(i).getLatitude(yField));
				geometry.put("type", "Point");
				geometry.putPOJO("coordinates", coord);

				// properies anlegen
				ObjectNode properties = mapper.valueToTree(points.get(i).getPoints());

				// Feature bestandteile hinzufügen
				feature1.putPOJO("properties", properties);
				feature1.putPOJO("geometry", geometry);

				features.add(feature1);
				// break;
			}
			collectionNode.put("type", "FeatureCollection");
			collectionNode.putPOJO("features", features);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(collectionNode);

		} catch (JsonProcessingException e) {
			throw e;
		}

	}

}
