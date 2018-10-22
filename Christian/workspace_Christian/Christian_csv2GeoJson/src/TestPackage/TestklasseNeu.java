package TestPackage;

import java.io.BufferedReader;
import java.util.ArrayList;

import CSV2GeoJSON.GeoJsonConverter;
import CSV2GeoJSON.Stadion;

public class TestklasseNeu {
	public static void main(String[] args) throws Exception{
		String dateipfad = "C:\\Users\\chris\\OneDrive for Business\\SHK_Stelle\\CSV2GEOJSON\\stadien.properties";
		GeoJsonConverter kurowski = new GeoJsonConverter(dateipfad, "url", "fieldSep", "xField", "yField", "ort");
		BufferedReader buffR = kurowski.readWebsite();
		ArrayList<Stadion> stadien =  kurowski.readCSV(buffR);
		String geoJson = kurowski.createGEOJSON(stadien);
		System.out.println(geoJson);
	}
}
