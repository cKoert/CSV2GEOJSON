package BibliothekenTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StadionTest {

	public static void main(String[] args) throws Exception{
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("https://raw.githubusercontent.com/jokecamp/FootballData/master/other/stadiums-with-GPS-coordinates.csv");
		CloseableHttpResponse antwort = httpclient.execute(httpGet);
		
		//Fehler bei Website aufrufen
		
		//StringBuffer csvString = new StringBuffer("");
		String csvString = "";
		String[] csvArray;
		List<Stadion> csvList = new ArrayList<Stadion>();
		
		try {
			HttpEntity entity = antwort.getEntity();
			InputStream content = entity.getContent();
			BufferedReader rd = new BufferedReader(new InputStreamReader(content));

			String line = "";
			Boolean firstLine = true;
			while ((line = rd.readLine()) != null) {
				//csvString = csvString + line + "\n";
				csvString = line;
				csvArray = line.split(",");
				//System.out.println(csvArray);
				if(firstLine == false) {
					Stadion stad = new Stadion(csvArray[0], csvArray[1], csvArray[2], csvArray[3], csvArray[4], csvArray[5], csvArray[6], csvArray[7]);
					csvList.add(stad);
				}else {
					firstLine = false;
				}
			}
			//EntityUtils.consume(entity1);
		} finally {
		    antwort.close();
		}
		
		//System.out.println(csvString);
		
		
		//Aufbereitung des CSV-Strings
		
		//Stadion a = new Stadion("FC-Tim", "FC-Tim", "Iserlohn", "Kuro-Stadion", "2", "51.376128f", "7.674340f", "Deutschland");
		String jsonString = "";
		
		ObjectMapper mapper = new ObjectMapper();
		try{
			for(int i = 0; i <= csvList.size() - 1; i++) {
				jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(csvList.get(i));
				System.out.println(jsonString);
			}
		}
		catch (JsonParseException e) { e.printStackTrace();}
		catch (JsonMappingException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace();}

	}
}

class Stadion {
	
	private String team;
	private String fdcouk;
	private String city;
	private String stadium;
	private int capacity;
	private float lat;
	private float lon;
	private String country;
	
	public Stadion() {}
	public Stadion(String team, String fdcouk, String city, String stadium, String capacity, String lat, String lon, String country) {
		this. team = team;
		this.fdcouk = fdcouk;
		this.city = city;
		this.stadium = stadium;
		this.capacity = Integer.parseInt(capacity);
		this.lat = Float.parseFloat(lat);
		this.lon = Float.parseFloat(lon);
		this.country = country;
	}

	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getFdcouk() {
		return fdcouk;
	}
	public void setFdcouk(String fdcouk) {
		this.fdcouk = fdcouk;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStadium() {
		return stadium;
	}
	public void setStadium(String stadium) {
		this.stadium = stadium;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLon() {
		return lon;
	}
	public void setLon(float lon) {
		this.lon = lon;
	}
	
}



