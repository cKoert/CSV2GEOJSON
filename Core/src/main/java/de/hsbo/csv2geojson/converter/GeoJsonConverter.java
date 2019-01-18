package de.hsbo.csv2geojson.converter;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import de.hsbo.csv2geojson.geometry.CsvPoint;

/**
 * This class converts the ArrayList to a GeoJSON-file constructor uses points
 * with coordinate describing column names using GeoJSON-syntax
 */

public class GeoJsonConverter {

	// attributes
	private ArrayList<CsvPoint> pointList;



	// constructor
	public GeoJsonConverter(ArrayList<CsvPoint> pointList) {
		super();
		this.pointList = pointList;
	}

	// convert
	public String createGEOJSON() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode collectionNode = mapper.createObjectNode();
		ArrayNode features = mapper.createArrayNode();

		for (int i = 0; i < pointList.size(); i++) {
			// create GeoJSON nodes
			ObjectNode feature = mapper.createObjectNode();
			ObjectNode geometry = mapper.createObjectNode();

			feature.put("type", "Feature");
			// fill geometry node
			Double[] coord = new Double[2];
			coord[0] = Double.parseDouble(pointList.get(i).getLongitude());
			coord[1] = Double.parseDouble(pointList.get(i).getLatitude());
			geometry.put("type", "Point");
			geometry.putPOJO("coordinates", coord);

			// create node
			ObjectNode properties = mapper.valueToTree(pointList.get(i).getAttributes());

			// add properties and geometry to feature node
			feature.putPOJO("properties", properties);
			feature.putPOJO("geometry", geometry);

			features.add(feature);
		}
		collectionNode.put("type", "FeatureCollection");
		collectionNode.putPOJO("features", features);
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(collectionNode);

	}


	// Get and Set methods
	public ArrayList<CsvPoint> getPointList() {
		return pointList;
	}

	public void setPointList(ArrayList<CsvPoint> pointList) {
		this.pointList = pointList;
	}
}
