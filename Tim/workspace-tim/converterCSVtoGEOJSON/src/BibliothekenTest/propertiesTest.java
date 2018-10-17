package BibliothekenTest;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class propertiesTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Properties properties = new Properties();
			BufferedInputStream stream = new BufferedInputStream(new FileInputStream("E:\\Google Drive\\Arbeit\\GIT\\CSV2GEOJSON\\stadien.properties"));
			properties.load(stream);
			stream.close();

			String url = properties.getProperty("url");
			String fieldSep = properties.getProperty("fieldSep");
			String xFeld = properties.getProperty("xFeld");
			String yFeld = properties.getProperty("yFeld");
			String ort = properties.getProperty("ort");

			System.out.println(properties.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
