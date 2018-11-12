# CSV2GEOJSON-Converter
 
 ## Description
 This tool converts a CSV-File (URL required) with point geometry features into a GeoJSON-file.
 
 ## Installation
 Create a jar file from the java classes and external libraries. Following, you can run the jar in a command prompt.
 
 ## Usage
 You have to create a .properties-file, which contains all necessary informations.
 These informations are the
 * `url` url of the CSV-file
 * `fieldSep` field separator
 * `xField` column name of the longitude
 * `yField` column name of the latitude
 * `target` path of target file
 * `relevantFields` relevant columns  you want to have displayed as attributes in the GeoJSON-File
 
 ##### Example for a properties-file
 ```
url https://raw.githubusercontent.com/jokecamp/FootballData/master/other/stadiums-with-GPS-coordinates.csv
fieldSep ,
xField Longitude
yField Latitude
target .\\\stadiums-with-GPS-coordinates.geojson
relevantFields Team,City,Stadium,Capacity,Latitude,Longitude,Country
```

To start the application you have to use a command-line interface.</br>
Navigate to the directory of the jar-file, execute it and add an argument with the path of the properties-file:

##### Example for a statemant
`java -jar ConverterCSV2GEOJSON.jar C:\stadiums.properties `

## External Libraries
HttpClient (Apache Software Foundation)
JACKSON (Apache Software Foundation)

## Credits
Project created by ckoert, timkuro and Atachon
