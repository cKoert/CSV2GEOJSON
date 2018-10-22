package Experimente;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Properties;



public class Testklasse2 {
	
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		
		
		// Properties Datei lesen
		Properties properties = new Properties();
		try {
		BufferedInputStream stream = new BufferedInputStream(new FileInputStream(
				"C:\\Users\\chris\\OneDrive for Business\\SHK_Stelle\\CSV2GEOJSON\\stadien.properties"));
			properties.load(stream);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String url = properties.getProperty("url");
		String fieldSep = properties.getProperty("fieldSep");
		String xField = properties.getProperty("xField");
		String yField = properties.getProperty("yField");
		String ort = properties.getProperty("ort");

		// HTTP kontaktieren
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response1 = httpclient.execute(httpGet); // wirft excepion aus , führt httpget aus

		// CSV lesen
		BufferedReader buffR;
		//List stadien = new ArrayList<Stadion>();

		// Json erstellen
		String jsonString = "";
		ObjectMapper mapper = new ObjectMapper();	//"Vorgabe" der JacksonBib
		
		
		try {
			// Http Objekt anlegen
			HttpEntity entity1 = response1.getEntity(); // ein HttpObjekt wird erzeugt,bzw gefüllt (Statusleiste,											// Parameter, Content)
			InputStream httpcontent1 = entity1.getContent(); // Inhalt abfragen
			InputStreamReader inStream = new InputStreamReader(httpcontent1); // Inhalt wird gelesen
			buffR = new BufferedReader(inStream);
			
			// Damit Schleife funktioniert
			String line = "";	//Leerer Startwert
			// Erste Zeile filtern	
			String[] feldNamen;			//Spaltenüberschriften
			boolean ersteZeile = true;	

			while ((line = buffR.readLine()) != null) {
				String[] stadAr = line.split(fieldSep);
				if (ersteZeile == true) {
					feldNamen = stadAr;
					ersteZeile = false;
				} else {
					Stadion stadion = new Stadion(stadAr[0], stadAr[1], stadAr[2], stadAr[3], stadAr[4], stadAr[5],
							stadAr[6], stadAr[7]);
					//stadien.add(stadion);
					jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(stadion); // nutzt getter-Methoden
				}
				// byte[] jsonByte = mapper.writeValueAsBytes(stadion);
				// System.out.println(jsonByte);
				//System.out.println(jsonString);	
				
			}
			//EntityUtils.consume(entity1);
		} finally {
			response1.close();
		}
				
		ObjectNode collectionNode = mapper.createObjectNode();
		ArrayNode features = mapper.createArrayNode();
		ObjectNode feature1 = mapper.createObjectNode();
		
		ObjectNode properties1 = mapper.createObjectNode();
		ObjectNode geometry = mapper.createObjectNode();
		Double[] coord = new Double[2];
		coord[0] = 51.555;
		coord[1] = -0.108611;
		
		geometry.put("type", "Point");
		geometry.putPOJO("coordinates", coord);
		
        feature1.put("type", "Feature");
        feature1.putPOJO("properties", properties1);
        feature1.putPOJO("geometry", geometry);
		
        features.add(feature1);
		
		collectionNode.put("type","FeatureCollection");
		//collectionNode.put("features", features);
		collectionNode.putPOJO("features", features);
		
		System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(collectionNode));
		System.out.println();
		//System.out.println(stadien.toString());
	}

}
