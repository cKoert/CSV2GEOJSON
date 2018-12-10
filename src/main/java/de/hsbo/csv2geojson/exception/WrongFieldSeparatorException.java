package de.hsbo.csv2geojson.exception;

public class WrongFieldSeparatorException extends Exception {
	
	public WrongFieldSeparatorException() {
		super("Wrong Fieldseparator. Only ',' and ';' are allowed.");
	}
	
}
