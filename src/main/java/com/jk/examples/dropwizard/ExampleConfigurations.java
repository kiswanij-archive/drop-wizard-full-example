package com.jk.examples.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.db.DatabaseConfiguration;

/**
 * This class contains the configurations of this service. The confgurations is
 * available in config.xml file which is in YAML format.
 * 
 * @author Jalal Kiswani
 * 
 */
public class ExampleConfigurations extends Configuration {
	/**
	 * Database Configuration instance
	 */
	private DatabaseConfiguration database = new DatabaseConfiguration();

	/**
	 * The maximum number of days to consider the visit outDated
	 */
	@JsonProperty
	private int visitListMaxDays = 10;

	/**
	 * Number of threads in the UsersFacade to records the visits log
	 */
	@JsonProperty
	private int databaseThreadsPoolSize = 5;

	/**
	 * The seprator used in the format visits list
	 */
	@JsonProperty
	private String outputSeprator = System.getProperty("line.separator");

	/**
	 * getter for database configuration
	 * 
	 * @return
	 */
	public DatabaseConfiguration getDatabase() {
		return database;
	}

	/**
	 * Getter for visitListMaxDays
	 * 
	 * @return
	 */
	public int getVisitListMaxDays() {
		return visitListMaxDays;
	}

	/**
	 * Getter for DatabseThreadPoolSize
	 * 
	 * @return
	 */
	public int getDatabaseThreadsPoolSize() {
		return databaseThreadsPoolSize;
	}

	/**
	 * Gettre for outputSepartor
	 * 
	 * @return
	 */
	public String getOutputSeprator() {
		return outputSeprator;
	}

}
