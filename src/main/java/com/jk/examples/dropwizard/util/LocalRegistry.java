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
	 * Getter for DBI variable
	 * 
	 * @return
	 */
	public static DBI getDbi() {
		return dbi;
	}

	/**
	 * Setter for DBU variable
	 * 
	 * @param dbi
	 */
	public static void setDbi(DBI dbi) {
		LocalRegistry.dbi = dbi;
	}

	/**
	 * Getter for envionment variable
	 * 
	 * @return
	 */
	public static Environment getEnvironment() {
		return environment;
	}

	/**
	 * Setter for envionment variable
	 * 
	 * @param environment
	 */
	public static void setEnvironment(Environment environment) {
		LocalRegistry.environment = environment;
	}

	/**
	 * Getter for configuration variable
	 * 
	 * @return
	 */
	public static ExampleConfigurations getConfigurations() {
		return configurations;
	}

	/**
	 * Setter for Configuration variable
	 * 
	 * @param configurations
	 */
	public static void setConfigurations(ExampleConfigurations configurations) {
		LocalRegistry.configurations = configurations;
	}

}
