package com.connectgroup.data;

/**
 * Log data entries.
 * @author Amjad
 *
 */
public class LogEntry {
	
	/**
	 * The request timestamp.
	 */
	private long requestTimestamp;
	
	/**
	 * The country code.
	 */
	private String countryCode;
	
	/**
	 * The response time.
	 */
	private long responseTime;
	
	public LogEntry(long requestTimestamp, String countryCode, long responseTime) {
		this.requestTimestamp = requestTimestamp;
		this.countryCode = countryCode;
		this.responseTime = responseTime;
	}

	/**
	 * Gets the request timestamp.
	 * @return requestTimestamp
	 */
	public long getRequestTimestamp() {
		return requestTimestamp;
	}

	/**
	 * Sets the request timestamp. 
	 * @param requestTimestamp
	 */
	public void setRequestTimestamp(long requestTimestamp) {
		this.requestTimestamp = requestTimestamp;
	}

	/**
	 * Gets the country code.
	 * @return countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * Sets the country code. 
	 * @param countryCode
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * Gets the response time.
	 * @return responseTime
	 */
	public long getResponseTime() {
		return responseTime;
	}
	
	/**
	 * Sets the response time.
	 * @param responseTime
	 */
	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}

}
