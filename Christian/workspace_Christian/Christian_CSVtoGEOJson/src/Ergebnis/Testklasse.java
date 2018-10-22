package Ergebnis;

import java.io.BufferedReader;
import java.util.ArrayList;

import CSVtoGeoJson.Stadion;

public class Testklasse {

	public static void main(String[] args) throws Exception{
		String dateipfad = "C:\\Users\\chris\\OneDrive for Business\\SHK_Stelle\\CSV2GEOJSON\\stadien.properties";
		PropertiesObject prop = new PropertiesObject(dateipfad);
		HtmlObject html = new HtmlObject(prop.getPropUrl());
		
		BufferedReader kurowski = html.readWebsite();
		CsvObject csv = new CsvObject(kurowski, prop.getPropSep(), prop.getPropXField(), prop.getPropYField());
		
		ArrayList<Stadion> blindert = csv.readCSV();
		GeoJsonObject geoJson = new GeoJsonObject(blindert);
		
		String geoJsonString = geoJson.createGEOJSON();
		
		System.out.println(geoJsonString);

	}

}
