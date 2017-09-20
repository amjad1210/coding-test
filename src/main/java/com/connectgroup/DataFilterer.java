package com.connectgroup;

import java.io.Reader;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.connectgroup.data.LogEntry;
import com.connectgroup.util.FileParser;

public class DataFilterer {

	/**
	 * Filters the log entry list by country.
	 * @param source
	 * @param country
	 * @return filteredData
	 */
	public static Collection<?> filterByCountry(Reader source, String country) {
		List<LogEntry> data = FileParser.readData(source);

		List<LogEntry> filteredData = data.stream()
				.filter(e -> e.getCountryCode().equals(country))
				.collect(Collectors.toList());

		return filteredData;
	}

	/**
	 * Filters the log entry list by country with a response time above limit.
	 * @param source
	 * @param country
	 * @param limit
	 * @return filteredData
	 */
	public static Collection<?> filterByCountryWithResponseTimeAboveLimit(Reader source, String country, long limit) {
		List<LogEntry> filteredData = ((List<LogEntry>) filterByCountry(source, country)).stream()
				.filter(e -> e.getResponseTime() > limit)
				.collect(Collectors.toList());

		return filteredData;
	}

	/**
	 * Filter the log entry list with response times above average.
	 * @param source
	 * @return filteredData
	 */
	public static Collection<?> filterByResponseTimeAboveAverage(Reader source) {
		List<LogEntry> data = FileParser.readData(source);
		
		if(data.isEmpty()) {
			return Collections.emptyList();
		}
		
		//Calculate the average first and refilter.
		double averageResponseTime = data.stream()
				.mapToLong(e -> e.getResponseTime())
				.average().getAsDouble();

		List<LogEntry> filteredData = data.stream()
				.filter(e -> e.getResponseTime() > averageResponseTime)
				.collect(Collectors.toList());

		return filteredData;
	}

}