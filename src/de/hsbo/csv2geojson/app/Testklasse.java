package de.hsbo.csv2geojson.app;

import java.io.BufferedReader;
import java.util.ArrayList;


import de.hsbo.csv2geojson.converter.CsvObject;
import de.hsbo.csv2geojson.converter.GeoJsonObject;
import de.hsbo.csv2geojson.converter.HtmlObject;
import de.hsbo.csv2geojson.converter.PropertiesObject;
import de.hsbo.csv2geojson.geometry.Stadion;


public class Testklasse{

	public static void main(String[] args) throws Exception{
		
		String dateipfad = "E:\\Google Drive\\Arbeit\\Projekt_CSV2GEOJSON\\workspace-wacodis\\CSV2GEOJSON\\stadien.properties";
		//"C:\\Users\\chris\\OneDrive for Business\\SHK_Stelle\\CSV2GEOJSON\\stadien.properties"
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
		System.out.println();
		System.out.println(prop.getPropDest());
		// System.out.println(prop.getPropUrl());
		// System.out.println(geoJsonString);
		System.out.println(prop.getSepFields());
	}

}
