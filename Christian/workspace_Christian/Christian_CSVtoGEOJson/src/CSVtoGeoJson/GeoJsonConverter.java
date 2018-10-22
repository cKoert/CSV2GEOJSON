package CSVtoGeoJson;



import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class GeoJsonConverter {

	// Attribute
	private String propUrl;
	private String propSep;
	private String propXField;
	private String propYField;
	private String propZiel;

	// Properties-Datei
	public GeoJsonConverter(String proptiesSpeicher, String propUrl, String propSep, String propXField, String propYField, String propZiel) {
		// Einlesen
		Properties properties = new Properties();
		try {
		BufferedInputStream stream = new BufferedInputStream(new FileInputStream(proptiesSpeicher));
			properties.load(stream);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Inhalte der Properties-Datei zuweisen
		this.propUrl = properties.getProperty("url");
		this.propSep = properties.getProperty("fieldSep");
		this.propXField = properties.getProperty("xFeld");
		this.propYField = properties.getProperty("yFeld");
		this.propZiel = properties.getProperty("ort");
	}

	//  Website einlesen
	public BufferedReader readWebsite() throws Exception {
		try {
			// HTTP-Client kontaktieren
			BufferedReader buffR;
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(propUrl);
			CloseableHttpResponse response1 = httpclient.execute(httpGet);
			
			HttpEntity entity1 = response1.getEntity(); // ein HttpObjekt wird erzeugt,bzw gefüllt (Statusleiste, Parameter, Content)
			InputStream httpcontent1 = entity1.getContent(); // Inhalt abfragen
			InputStreamReader inStream = new InputStreamReader(httpcontent1); // Inhalt wird gelesen
			buffR = new BufferedReader(inStream);
			return buffR;
		} catch (ClientProtocolException c) {
			throw c;
		} catch (IOException e) { // immer zum Schluss
			throw e;
		}
	}

	// CSV lesen
	public ArrayList<Stadion> readCSV(BufferedReader buffR1) throws Exception {
		
		try {
			BufferedReader buffR = buffR1;
			ArrayList<Stadion> stadien = new ArrayList<Stadion>();
			// Damit Schleife funktioniert
			String line = ""; // Leerer Startwert
			// Erste Zeile filtern
			String[] feldNamen; // Spaltenüberschriften
			boolean ersteZeile = true;
			while ((line = buffR.readLine()) != null) {
				String[] stadAr = line.split(propSep);
				if (ersteZeile == true) {
					feldNamen = stadAr;
					ersteZeile = false;
				} else {
					Stadion stadion = new Stadion(stadAr[0], stadAr[1], stadAr[2], stadAr[3], stadAr[4], stadAr[5],
							stadAr[6], stadAr[7]);
					stadien.add(stadion);
				}
			}
			return stadien;
		} catch (IOException e) {
			throw e;
		}
		
	}

	// Umwandlung in GeoJSON
	public String createGEOJSON(ArrayList<Stadion> stadien1) throws Exception {
		try {
			String jsonString = "";
			ArrayList<Stadion> stadien = stadien1;
			ObjectMapper mapper = new ObjectMapper();
			
			ObjectNode collectionNode = mapper.createObjectNode();
			ArrayNode features = mapper.createArrayNode();
			
			for (int i = 0; i < stadien.size(); i++) {				
				//GEOJson anlegen
				ObjectNode feature1 = mapper.createObjectNode();
				ObjectNode properties1 = mapper.createObjectNode();
				ObjectNode geometry = mapper.createObjectNode();
				
				feature1.put("type", "Feature");
				//geometry anlegen
				Double[] coord = new Double[2];
				coord[0] = Double.parseDouble(stadien.get(i).getLongitude());
				coord[1] = Double.parseDouble(stadien.get(i).getLatitude());
				geometry.put("type", "Point");
				geometry.putPOJO("coordinates", coord);
				
				//properies anlegen
				ObjectNode properties = mapper.valueToTree(stadien.get(i));
				
				//Feature bestandteile hinzufügen
		        feature1.putPOJO("properties", properties);
		        feature1.putPOJO("geometry", geometry);
		        
		        features.add(feature1);
		        //break;
			}
		collectionNode.put("type","FeatureCollection");
		collectionNode.putPOJO("features", features);
		System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(collectionNode));
		return "Ende";
		} catch (JsonProcessingException e) {
			throw e;
		}
		
	}
}

