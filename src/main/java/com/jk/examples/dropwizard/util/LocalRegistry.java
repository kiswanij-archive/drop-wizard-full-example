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
package com.jk.examples.dropwizard.util;

import org.skife.jdbi.v2.DBI;

import com.jk.examples.dropwizard.ExampleConfigurations;
import com.yammer.dropwizard.config.Environment;

/**
 * This class is Local Repository for the important resources and objects
 * initilized by the AssignmentService , which is: 1- DBI : JDBI database main
 * object which is initialized using YAML config file by the Service 2-
 * Environment : environment variable 3- Configurations :which is instance of
 * YAML configuration
 *
 * @author Jalal Kiswani
 *
 */
public class LocalRegistry {

	/**
	 * instance of DBI
	 */
	private static DBI dbi;

	/**
	 * Instance of Environment
	 */
	private static Environment environment;

	/**
	 * Instance of service Configuratoins
	 */
	private static ExampleConfigurations configurations;

	/**
	 * Getter for configuration variable
	 *
	 * @return
	 */
	public static ExampleConfigurations getConfigurations() {
		return LocalRegistry.configurations;
	}

	/**
	 * Getter for DBI variable
	 *
	 * @return
	 */
	public static DBI getDbi() {
		return LocalRegistry.dbi;
	}

	/**
	 * Getter for envionment variable
	 *
	 * @return
	 */
	public static Environment getEnvironment() {
		return LocalRegistry.environment;
	}

	/**
	 * Setter for Configuration variable
	 *
	 * @param configurations
	 */
	public static void setConfigurations(final ExampleConfigurations configurations) {
		LocalRegistry.configurations = configurations;
	}

	/**
	 * Setter for DBU variable
	 *
	 * @param dbi
	 */
	public static void setDbi(final DBI dbi) {
		LocalRegistry.dbi = dbi;
	}

	/**
	 * Setter for envionment variable
	 *
	 * @param environment
	 */
	public static void setEnvironment(final Environment environment) {
		LocalRegistry.environment = environment;
	}

}
