package EinarbeitenBibliotheken;

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

public class Testklasse2 {
	
	public static void main(String[] args) throws Exception {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("https://raw.githubusercontent.com/jokecamp/FootballData/master/other/stadiums-with-GPS-coordinates.csv");
		CloseableHttpResponse response1 = httpclient.execute(httpGet); //wirft excepion aus , führt httpget aus
		BufferedReader buffR;
		StringBuffer sBuff = new StringBuffer();

		try {
			HttpEntity entity1 = response1.getEntity();	//ein HttpObjekt wird erzeugt,bzw gefüllt (Statusleiste, Parameter, Content)
			InputStream httpcontent1 = entity1.getContent(); //Inhalt abfragen
			InputStreamReader inStream = new InputStreamReader(httpcontent1); //Inhalt wird gelesen
			buffR = new BufferedReader(inStream);
			
			String line = "";
			while ((line = buffR.readLine()) != null) {
				sBuff.append(line + "\n");
			}
			EntityUtils.consume(entity1);
			System.out.println();
			HttpEntity entity2 = response1.getEntity();
			InputStream httpcontent2 = entity1.getContent();
			InputStreamReader inStream2 = new InputStreamReader(httpcontent1);
			BufferedReader buffR2 = new BufferedReader(inStream2);
			StringBuffer sBuff2 = new StringBuffer();
			String line2 = "";
			while ((line2 = buffR.readLine()) != null) {
				sBuff2.append(line2 + "\n");
			}
			
		} finally {
			response1.close();
		}
		System.out.println(sBuff.toString());

	}

}
