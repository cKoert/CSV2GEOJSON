package de.hsbo.csv2geojson.converter;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * This class reads URL of the CSV-File from the properties-File. The class
 * transfers it into a BufferedReader
 */

public class HtmlReader {

	// attributes
	String propUrl;

	// constructor
	public HtmlReader(String propUrl) {
		this.propUrl = propUrl;
	}

	public InputStream readWebsite() throws ClientProtocolException, IOException {
		
			// contact http-client
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(propUrl);
			CloseableHttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity(); // fill http-Object (status, parameters, content)
			InputStream httpcontent = entity.getContent(); // ask for content
			return httpcontent;
	}

}
