package BibliothekenTest;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestHTTPClientOfficial {

	public static void main(String[] args) throws Exception {

		// Properties-Datei einlesen
		Properties properties = new Properties();
		BufferedInputStream stream = new BufferedInputStream(
				new FileInputStream("E:\\Google Drive\\Arbeit\\GIT\\CSV2GEOJSON\\stadien.properties"));
		try {
			properties.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		stream.close();

		// Eigenschaften properties
		String url = properties.getProperty("url");
		String fieldSep = properties.getProperty("fieldSep");
		String xFeld = properties.getProperty("xFeld");
		String yFeld = properties.getProperty("yFeld");
		String ort = properties.getProperty("ort");

		// Http-Client kontaktieren
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response1 = httpclient.execute(httpGet); // response1 greift auf Link zu

		// CSV einlesen
		BufferedReader buffR;
		
		//Json
		String jsonString="";
		ObjectMapper mapper = new ObjectMapper();

		try {
			// variable response1 mit Parametern füllen
			HttpEntity entity1 = response1.getEntity();
			// Inhalt zuweisen
			InputStream httpContent1 = entity1.getContent();
			// Instanz anlegen
			InputStreamReader inStream = new InputStreamReader(httpContent1);
			// BufferedReader buffR Objekt zuweisen
			buffR = new BufferedReader(inStream);

			String line = ""; // leerer Startwert (!= null)
			String [] feldNamen;
			boolean firstLine = true;
			
			while ((line = buffR.readLine()) != null) { // Zeilen durchlaufen

				String[] elements = line.split(fieldSep); // Elemente aus Spalten filtern
				if(firstLine ==true) {
					feldNamen=elements;
					firstLine = false;
				}
				
				else {
					Stadion stadion = new Stadion(elements[0], elements[1], elements[2], elements[3], elements[4], elements[5], elements[6], elements[7]);
					jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(stadion);
				}
				
				System.out.println(jsonString);
			}

		} finally {
			response1.close();
		}
		// System.out.println(sBuff.toString());

	}
}
