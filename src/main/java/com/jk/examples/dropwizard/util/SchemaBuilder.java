package com.jk.examples.dropwizard.util;

import java.io.IOException;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

/**
 * Since we are using File-based Database (H2) , we created this class to make
 * the database administration more reliable and effience. We seprated the
 * Creats tables statements in create-db.sql file , and the drop tables
 * statements in drop-db.sql .
 * 
 * @author Jalal Kiswani
 * 
 */
public class SchemaBuilder {
	/**
	 * Name of the file contains the structor creation script
	 */
	public static String CREATE_SCRIPT_FILE_NAME = "/create-db.sql";

	/**
	 * Name of the file contains the structor drop script
	 */
	public static String DROP_SCRIPT_FILE_NAME = "/drop-db.sql";

	/**
	 * Actual Schema creation script
	 */
	private String schemaCreationScript;

	/**
	 * Actual schema dropp script
	 */
	private String schemaDroppScript;

	/**
	 * Default constructor which load the actual craete and drop script
	 * 
	 * @throws IOException
	 */
	public SchemaBuilder() throws IOException {
		schemaCreationScript = FilesUtil.readResourceFile(CREATE_SCRIPT_FILE_NAME);
		schemaDroppScript = FilesUtil.readResourceFile(DROP_SCRIPT_FILE_NAME);
	}

	/**
	 * execute the schemaCreationScript using JDBI library
	 */
	public void createTables() {
		DBI dbi = LocalRegistry.getDbi();
		Handle handle = dbi.open();
		try {
			handle.execute(schemaCreationScript);
		} finally {
			handle.close();
		}
	}

	/**
	 * execute the schemaDroppScript using JDBI library
	 */
	public void dropTables() {
		DBI dbi = LocalRegistry.getDbi();
		Handle handle = dbi.open();
		try {
			handle.execute(schemaDroppScript);
		} finally {
			handle.close();
		}
	}

}
