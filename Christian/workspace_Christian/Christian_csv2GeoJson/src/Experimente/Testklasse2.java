package Experimente;

import java.io.BufferedReader;
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

public class Testklasse2 {
	
	public static void main(String[] args) throws Exception {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("https://raw.githubusercontent.com/jokecamp/FootballData/master/other/stadiums-with-GPS-coordinates.csv");
		CloseableHttpResponse response1 = httpclient.execute(httpGet); //wirft excepion aus , f�hrt httpget aus
		BufferedReader buffR;
		//StringBuffer sBuff = new StringBuffer();
		
		String jsonString = "";
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JtsModule());

		try {
			HttpEntity entity1 = response1.getEntity();	//ein HttpObjekt wird erzeugt,bzw gef�llt (Statusleiste, Parameter, Content)
			InputStream httpcontent1 = entity1.getContent(); //Inhalt abfragen
			InputStreamReader inStream = new InputStreamReader(httpcontent1); //Inhalt wird gelesen
			buffR = new BufferedReader(inStream);
			
			
			String line = "";
			while ((line = buffR.readLine()) != null) {
				String[] stadAr = line.split(",");
				//sBuff.append(line + "\n");
				
				//Stadion stadion = new Stadion("Team" , "FDCOUK" ,"City", "Stadium" ,"6666","1000.0","1000.0","Country");
				
				Stadion stadion = new Stadion(stadAr[0], stadAr[1], stadAr[2], stadAr[3], stadAr[4], stadAr[5], stadAr[6], stadAr[7]);
				jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(stadion);	//nutzt getter-Methoden
				//byte[] jsonByte = mapper.writeValueAsBytes(stadion);
				//System.out.println(jsonByte);
				
				
				
				System.out.println(jsonString);
				
			}
			EntityUtils.consume(entity1);
			
		
		} finally {
			response1.close();
		}
	
		
	}

}
