/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hsbo.csv2geojson.converter;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:s.drost@52north.org">Sebastian Drost</a>
 */
public class CsvReaderTest {

    private static CsvReader reader;
    private InputStream in;

    @BeforeEach
    void setup() {
        in = getClass().getResourceAsStream("csv-test");
        reader = new CsvReader(in, ";", "lat", "lon");
    }

    @DisplayName("Tets reading CSV file with appropriate values")
    @Test
    void testReadAppropriateCsv() throws Exception {
        List relevantFields = Arrays.asList("name", "description");
        List points = reader.readCSV(relevantFields);

        Assertions.assertEquals(2, points.size());
    }

}
