package Ergebnis;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class GeoJsonObject {

	// Attribute
	ArrayList<Stadion> stadien1;

	// Konstrujtor
	public GeoJsonObject(ArrayList<Stadion> stadien1) {
		super();
		this.stadien1 = stadien1;
	}

	// Umwandlung in GeoJSON
	public String createGEOJSON() throws Exception {
		try {
			String jsonString = "";
			ArrayList<Stadion> stadien = stadien1;
			ObjectMapper mapper = new ObjectMapper();

			ObjectNode collectionNode = mapper.createObjectNode();
			ArrayNode features = mapper.createArrayNode();

			for (int i = 0; i < stadien.size(); i++) {
				// GEOJson anlegen
				ObjectNode feature1 = mapper.createObjectNode();
				ObjectNode properties1 = mapper.createObjectNode();
				ObjectNode geometry = mapper.createObjectNode();

				feature1.put("type", "Feature");
				// geometry anlegen
				Double[] coord = new Double[2];
				coord[0] = Double.parseDouble(stadien.get(i).getLongitude());
				coord[1] = Double.parseDouble(stadien.get(i).getLatitude());
				geometry.put("type", "Point");
				geometry.putPOJO("coordinates", coord);

				// properies anlegen
				ObjectNode properties = mapper.valueToTree(stadien.get(i));

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
