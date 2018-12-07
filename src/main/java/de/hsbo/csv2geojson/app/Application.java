package de.hsbo.csv2geojson.app;

import java.io.InputStream;
import java.util.ArrayList;


import de.hsbo.csv2geojson.converter.CsvReader;
import de.hsbo.csv2geojson.converter.GeoJsonFileWriter;
import de.hsbo.csv2geojson.converter.GeoJsonConverter;
import de.hsbo.csv2geojson.converter.HtmlReader;
import de.hsbo.csv2geojson.converter.PropertiesReader;
import de.hsbo.csv2geojson.geometry.CsvPoint;


public class Application{

	public static void main(String[] args) throws Exception{
		// long start = System.currentTimeMillis();
		String path =args[0];
		PropertiesReader prop = new PropertiesReader();
		prop.readProperties(path);
		HtmlReader html = new HtmlReader(prop.getPropUrl());
		
		InputStream htmlInput = html.readWebsite();
		CsvReader csv = new CsvReader(prop.getPropSep(), prop.getPropXField(), prop.getPropYField());
		
		ArrayList<CsvPoint> csvArrayList = csv.readCSV(htmlInput, prop.getRelFields());
		GeoJsonConverter geoJson = new GeoJsonConverter(csvArrayList, prop.getPropXField(), prop.getPropYField());
		
		String geoJsonString = geoJson.createGEOJSON();
		
		GeoJsonFileWriter file = new GeoJsonFileWriter(geoJsonString, prop.getPropTarget());
		file.createFile();
		// long end = System.currentTimeMillis();
		System.out.println("created geojson-file");
		// System.out.println(end-start);
		// System.out.println(prop.getPropUrl());
		//System.out.println(geoJsonString);
		//System.out.println(prop.getSepFields());
	}

}
