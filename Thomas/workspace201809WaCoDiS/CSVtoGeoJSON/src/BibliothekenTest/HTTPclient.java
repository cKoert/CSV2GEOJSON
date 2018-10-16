package BibliothekenTest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HTTPclient {

	public static void main(String[] args) throws Exception{
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("https://raw.githubusercontent.com/jokecamp/FootballData/master/other/stadiums-with-GPS-coordinates.csv");
		CloseableHttpResponse antwort = httpclient.execute(httpGet);
		
		//Fehler bei Website aufrufen
		
		StringBuffer csvString = new StringBuffer("");
		
		try {
			HttpEntity entity = antwort.getEntity();
			InputStream content = entity.getContent();
			BufferedReader rd = new BufferedReader(new InputStreamReader(content));

			String line = "";
			while ((line = rd.readLine()) != null) {
				csvString.append(line + "\n");
			}
			
			//EntityUtils.consume(entity1);
		} finally {
		    antwort.close();
		}
		
		System.out.println(csvString);
		
		

	}
}

class jackObj {
	private float lat;
	private float lon;
	private String eins;
    private String zwei;
    private String drei;
    private String vier;
    private String funf;
    private String sechs;
    private String sieben;
    public jackObj(){}
	   
    //public String toString(){
    //   return "Student [ name: "+name+", age: "+ age+ " ]";
    //}
}
