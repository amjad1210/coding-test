package com.connectgroup;

import org.junit.Test;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.*;

public class DataFiltererTest {
	
    @Test
    public void shouldReturnEmptyCollection_WhenLogFileIsEmpty() throws FileNotFoundException {
        assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/empty"), "GB").isEmpty());
        assertTrue(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/empty"), "DE", 100).isEmpty());
        assertTrue(DataFilterer.filterByResponseTimeAboveAverage(openFile("src/test/resources/empty")).isEmpty());
    }
    
    @Test
    public void filterByCountryCodeTest() throws FileNotFoundException {
    	assertFalse(DataFilterer.filterByCountry(openFile("src/test/resources/single-line"), "GB").isEmpty());
    	assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/single-line"), "US").isEmpty());
    	assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/single-line"), "DE").isEmpty());
    	
    	assertEquals(DataFilterer.filterByCountry(openFile("src/test/resources/multi-lines"), "US").size(), 3);
    	assertEquals(DataFilterer.filterByCountry(openFile("src/test/resources/multi-lines"), "GB").size(), 1);
    	assertEquals(DataFilterer.filterByCountry(openFile("src/test/resources/multi-lines"), "DE").size(), 1);
    }
    
    @Test
    public void filterByCountryWithResponseTimeAboveLimit() throws FileNotFoundException {
    	assertFalse(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/single-line"), "GB", 100).isEmpty());
    	assertTrue(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/single-line"), "GB", 200).isEmpty());
    	assertTrue(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/single-line"), "US", 100).isEmpty());
    	
    	assertEquals(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/multi-lines"), "US", 100).size(), 3);
    	assertEquals(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/multi-lines"), "US", 600).size(), 2);
    	assertEquals(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/multi-lines"), "GB", 30).size(), 1);
    	assertTrue(DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/multi-lines"), "GB", 80).isEmpty());
    }
    
    @Test
    public void filterByResponseTimeAboveAverage() throws FileNotFoundException {
    	assertTrue(DataFilterer.filterByResponseTimeAboveAverage(openFile("src/test/resources/single-line")).isEmpty());
    	
    	assertFalse(DataFilterer.filterByResponseTimeAboveAverage(openFile("src/test/resources/multi-lines")).isEmpty());    	
    	//average from multi line = 526
    	assertEquals(DataFilterer.filterByResponseTimeAboveAverage(openFile("src/test/resources/multi-lines")).size(), 3);
    }

    private FileReader openFile(String filename) throws FileNotFoundException {
        return new FileReader(new File(filename));
    }
}
