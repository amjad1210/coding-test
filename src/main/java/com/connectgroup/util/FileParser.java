package com.connectgroup.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

import com.connectgroup.data.LogEntry;

/**
 * Parses and validates log data enteries.
 * @author Amjad
 *
 */
public class FileParser {
	
	/**
	 * The valid column length.
	 */
	private static final int VALID_COLUMN_LENGTH = 3;
	
	/**
	 * Reads the log files and parses the lines. 
	 * @param source
	 * @return data
	 */
	public static List<LogEntry> readData(Reader source) {
		List<LogEntry> data = new LinkedList<>();
		
		BufferedReader br = new BufferedReader(source);
		
		String line = "";
		int lineNumber = 0;
		try {
			while((line = br.readLine()) != null) {
				//Ignore the header.
				lineNumber ++;				
				if(lineNumber == 1) {
                    continue;
                }
				
				String[] columns = line.split(",");
				//Check if line is valid.
				if(isValidEntry(columns)) {
					data.add(new LogEntry(Long.parseLong(columns[0]), columns[1], Long.parseLong(columns[2])));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return data;		
	}
	
	/**
	 * Checks if column length is valid.
	 * @param columns
	 * @return true/false
	 */
	private static boolean isValidEntry(String[] columns) {
		return columns.length == VALID_COLUMN_LENGTH;
	}

}
