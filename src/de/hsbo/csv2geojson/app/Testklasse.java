package de.hsbo.csv2geojson.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.ArrayList;


import de.hsbo.csv2geojson.converter.CsvReader;
import de.hsbo.csv2geojson.converter.GeoJsonFileWriter;
import de.hsbo.csv2geojson.converter.GeoJsonConverter;
import de.hsbo.csv2geojson.converter.HtmlReader;
import de.hsbo.csv2geojson.converter.PropertiesReader;
import de.hsbo.csv2geojson.geometry.CsvPoint;


public class Testklasse{

	public static void main(String[] args) throws Exception{
		
		String path =args[0];
		//"E:\\Google Drive\\Arbeit\\Projekt_CSV2GEOJSON\\workspace-wacodis\\CSV2GEOJSON\\stadien.properties"
		//"C:\\Users\chris\\OneDrive for Business\\SHK_Stelle\\workspace_WaCoDiS\\CSV2GEOJSON\\stadien.properties"
		PropertiesReader prop = new PropertiesReader();
		prop.readProperties(path);
		HtmlReader html = new HtmlReader(prop.getPropUrl());
		
		InputStream htmlInput = html.readWebsite();
		CsvReader csv = new CsvReader(htmlInput, prop.getPropSep(), prop.getPropXField(), prop.getPropYField());
		
		ArrayList<CsvPoint> blindert = csv.readCSV(prop.getRelFields());
		GeoJsonConverter geoJson = new GeoJsonConverter(blindert, prop.getPropXField(), prop.getPropYField());
		
		String geoJsonString = geoJson.createGEOJSON();
		
		GeoJsonFileWriter file = new GeoJsonFileWriter(geoJsonString, prop.getPropDest());
		file.createFile();
		
		System.out.println("created geojson-file");
		// System.out.println(prop.getPropUrl());
		//System.out.println(geoJsonString);
		//System.out.println(prop.getSepFields());
	}

}
