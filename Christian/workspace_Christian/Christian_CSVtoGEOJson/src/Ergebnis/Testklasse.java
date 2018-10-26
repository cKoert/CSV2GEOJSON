package Ergebnis;

import java.io.BufferedReader;
import java.util.ArrayList;


public class Testklasse {

	public static void main(String[] args) throws Exception{
		
		String dateipfad = args[0];
		PropertiesObject prop = new PropertiesObject(dateipfad);
		HtmlObject html = new HtmlObject(prop.getPropUrl());
		
		BufferedReader kurowski = html.readWebsite();
		CsvObject csv = new CsvObject(kurowski, prop.getPropSep(), prop.getPropXField(), prop.getPropYField());
		
		ArrayList<Stadion> blindert = csv.readCSV();
		GeoJsonObject geoJson = new GeoJsonObject(blindert);
		
		String geoJsonString = geoJson.createGEOJSON();
		/*
		GeoJsonFile file = new GeoJsonFile(geoJsonString, prop.getPropZiel());
		file.createFile();
		*/
		System.out.println(prop.getPropZiel());
		System.out.println(prop.getPropUrl());
		System.out.println(geoJsonString);

	}

}
