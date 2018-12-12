package de.hsbo.csv2geojson.app;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import com.fasterxml.jackson.core.JsonProcessingException;

import de.hsbo.csv2geojson.converter.CsvReader;
import de.hsbo.csv2geojson.converter.GeoJsonFileWriter;
import de.hsbo.csv2geojson.converter.GeoJsonConverter;
import de.hsbo.csv2geojson.converter.HtmlReader;
import de.hsbo.csv2geojson.converter.PropertiesReader;
import de.hsbo.csv2geojson.exception.WrongFieldSeparatorException;
import de.hsbo.csv2geojson.geometry.CsvPoint;


public class Application{

	public static void main(String[] args){
		// long start = System.currentTimeMillis();
		String path =args[0];
		PropertiesReader prop = new PropertiesReader();
		try {
		prop.readProperties(path);
		HtmlReader html = new HtmlReader(prop.getPropUrl());
		
		InputStream htmlInput = html.readWebsite();
		CsvReader csv = new CsvReader(prop.getPropSep(), prop.getPropXField(), prop.getPropYField());
		
		ArrayList<CsvPoint> csvArrayList = csv.readCSV(htmlInput, prop.getRelFields());
		GeoJsonConverter geoJson = new GeoJsonConverter(csvArrayList);
		
		String geoJsonString = geoJson.createGEOJSON();
		
		GeoJsonFileWriter file = new GeoJsonFileWriter(geoJsonString, prop.getPropTarget());
				
		file.createFile();
		}
		catch(ClientProtocolException e) {
			e.printStackTrace();
		}
		catch(JsonProcessingException e) {
			e.printStackTrace();
		}
		catch(WrongFieldSeparatorException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("created geojson-file");
	
	}

}
