package BibliothekenTest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class TestHTTPClientOfficial {

	public static void main(String[] args) throws Exception {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(
				"https://raw.githubusercontent.com/jokecamp/FootballData/master/other/stadiums-with-GPS-coordinates.csv");
		CloseableHttpResponse response1 = httpclient.execute(httpGet); // response1 greift auf Link zu

		BufferedReader buffR;
		StringBuffer sBuff = new StringBuffer();

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
			while ((line = buffR.readLine()) != null) {
				sBuff.append(line + "\n");
				System.out.print(sBuff.toString());
			}

			//EntityUtils.consume(entity1); ???

		} finally {
			response1.close();
		}
		System.out.println(sBuff.toString());

	}
}
