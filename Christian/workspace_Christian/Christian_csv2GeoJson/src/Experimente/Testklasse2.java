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

import java.util.Properties;

public class Testklasse2 {
	
	public static void main(String[] args) throws Exception {

		//Properties Datei
		Properties properties = new Properties();
		BufferedInputStream stream = new BufferedInputStream(new FileInputStream("C:\\Users\\chris\\OneDrive for Business\\SHK_Stelle\\CSV2GEOJSON\\stadien.properties"));
		
		try {
		properties.load(stream);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		stream.close();
		String url = properties.getProperty("url");
		String fieldSep = properties.getProperty("fieldSep");
		String xField = properties.getProperty("xField");
		String yField = properties.getProperty("yField");
		String ort = properties.getProperty("ort");
		
		//HTTP einlesen
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response1 = httpclient.execute(httpGet); //wirft excepion aus , führt httpget aus
		
		//CSV lesen
		BufferedReader buffR;
		
		
		List stadien = new ArrayList<Stadion>();
		
		//Json erstellen
		String jsonString = "";
		ObjectMapper mapper = new ObjectMapper();
		
		


		try {
			HttpEntity entity1 = response1.getEntity();	//ein HttpObjekt wird erzeugt,bzw gefüllt (Statusleiste, Parameter, Content)
			InputStream httpcontent1 = entity1.getContent(); //Inhalt abfragen
			InputStreamReader inStream = new InputStreamReader(httpcontent1); //Inhalt wird gelesen
			buffR = new BufferedReader(inStream);
			
			boolean ersteZeile = true;	
			String line = "";
			String[] feldNamen;
			
			while ((line = buffR.readLine()) != null) {
				String[] stadAr = line.split(fieldSep);
				if(ersteZeile == true) {
					feldNamen = stadAr;
					ersteZeile = false;
				}
				else {
					Stadion stadion = new Stadion(stadAr[0], stadAr[1], stadAr[2], stadAr[3], stadAr[4], stadAr[5], stadAr[6], stadAr[7]);
					stadien.add(stadion);
					jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(stadion);	//nutzt getter-Methoden
				
				}
				
				//byte[] jsonByte = mapper.writeValueAsBytes(stadion);
				//System.out.println(jsonByte);
				
				
				
				//System.out.println(jsonString);
				
			}
			EntityUtils.consume(entity1);
			
		
		} finally {
			response1.close();
		}
	
		
	}

}
