/*
 * Copyright 2002-2016 Jalal Kiswani.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
	
	/** Database Configuration instance. */
	private final DatabaseConfiguration database = new DatabaseConfiguration();

	/** The maximum number of days to consider the visit outDated. */
	@JsonProperty
	private final int visitListMaxDays = 10;

	/** Number of threads in the UsersFacade to records the visits log. */
	@JsonProperty
	private final int databaseThreadsPoolSize = 5;

	/** The seprator used in the format visits list. */
	@JsonProperty
	private final String outputSeprator = System.getProperty("line.separator");

	/**
	 * getter for database configuration.
	 *
	 * @return the database
	 */
	public DatabaseConfiguration getDatabase() {
		return this.database;
	}

	/**
	 * Getter for DatabseThreadPoolSize.
	 *
	 * @return the database threads pool size
	 */
	public int getDatabaseThreadsPoolSize() {
		return this.databaseThreadsPoolSize;
	}

	/**
	 * Gettre for outputSepartor.
	 *
	 * @return the output seprator
	 */
	public String getOutputSeprator() {
		return this.outputSeprator;
	}

	/**
	 * Getter for visitListMaxDays.
	 *
	 * @return the visit list max days
	 */
	public int getVisitListMaxDays() {
		return this.visitListMaxDays;
	}

}
