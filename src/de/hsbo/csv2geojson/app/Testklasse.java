package de.hsbo.csv2geojson.app;

import java.io.BufferedReader;
import java.util.ArrayList;


import de.hsbo.csv2geojson.converter.CsvReader;
import de.hsbo.csv2geojson.converter.GeoJsonFileWriter;
import de.hsbo.csv2geojson.converter.GeoJsonConverter;
import de.hsbo.csv2geojson.converter.HtmlReader;
import de.hsbo.csv2geojson.converter.PropertiesReader;
import de.hsbo.csv2geojson.geometry.CsvPoint;


public class Testklasse{

	public static void main(String[] args) throws Exception{
		
		String dateipfad =args[0];
		//"E:\\Google Drive\\Arbeit\\Projekt_CSV2GEOJSON\\workspace-wacodis\\CSV2GEOJSON\\stadien.properties"
		//"C:\\Users\chris\\OneDrive for Business\\SHK_Stelle\\workspace_WaCoDiS\\CSV2GEOJSON\\stadien.properties"
		PropertiesReader prop = new PropertiesReader(dateipfad);
		HtmlReader html = new HtmlReader(prop.getPropUrl());
		
		BufferedReader kurowski = html.readWebsite();
		CsvReader csv = new CsvReader(kurowski, prop.getPropSep(), prop.getPropXField(), prop.getPropYField());
		
		ArrayList<CsvPoint> blindert = csv.readCSV(prop.getSepFields());
		GeoJsonConverter geoJson = new GeoJsonConverter(blindert, prop.getPropXField(), prop.getPropYField());
		
		String geoJsonString = geoJson.createGEOJSON();
		
		GeoJsonFileWriter file = new GeoJsonFileWriter(geoJsonString, prop.getPropDest());
		file.createFile();
		
		System.out.println();
		System.out.println(prop.getPropDest());
		// System.out.println(prop.getPropUrl());
		//System.out.println(geoJsonString);
		//System.out.println(prop.getSepFields());
	}

}
