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

/**
 * This class reads URL of the CSV-File from the properties-File.
 * The class transfers it into a BufferedReader
 */

public class HtmlReader {

	// attributes
	String propUrl;

	// constructor
	public HtmlReader(String propUrl) {
		this.propUrl = propUrl;
	}

	public BufferedReader readWebsite() throws Exception {
		try {
			// contact http-client
			BufferedReader buffR;
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(propUrl);
			CloseableHttpResponse response1 = httpclient.execute(httpGet);

			HttpEntity entity1 = response1.getEntity(); // fill http-Object (status, parameters, content)
			InputStream httpcontent1 = entity1.getContent(); // ask for content
			InputStreamReader inStream = new InputStreamReader(httpcontent1); // read content
			buffR = new BufferedReader(inStream);
			return buffR;
		} catch (ClientProtocolException c) {
			throw c;
		} catch (IOException e) {
			throw e;
		}
	}

}
