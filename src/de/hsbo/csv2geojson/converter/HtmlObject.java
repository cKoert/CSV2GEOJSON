package de.hsbo.csv2geojson.converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HtmlObject {

	// Attribute
	String propUrl;

	// Konstruktor
	public HtmlObject(String propUrl) {
		this.propUrl = propUrl;
	}

	//Methode
	public BufferedReader readWebsite() throws Exception {
		try {
			// HTTP-Client kontaktieren
			BufferedReader buffR;
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(propUrl);
			CloseableHttpResponse response1 = httpclient.execute(httpGet);

			HttpEntity entity1 = response1.getEntity(); // ein HttpObjekt wird erzeugt,bzw gefüllt (Statusleiste,
														// Parameter, Content)
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

}
