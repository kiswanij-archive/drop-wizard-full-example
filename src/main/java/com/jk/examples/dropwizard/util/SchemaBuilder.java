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

	/** Name of the file contains the structor creation script. */
	public static String CREATE_SCRIPT_FILE_NAME = "/create-db.sql";

	/** Name of the file contains the structor drop script. */
	public static String DROP_SCRIPT_FILE_NAME = "/drop-db.sql";

	/** Actual Schema creation script. */
	private final String schemaCreationScript;

	/** Actual schema dropp script. */
	private final String schemaDroppScript;

	/**
	 * Default constructor which load the actual craete and drop script.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public SchemaBuilder() throws IOException {
		this.schemaCreationScript = FilesUtil.readResourceFile(SchemaBuilder.CREATE_SCRIPT_FILE_NAME);
		this.schemaDroppScript = FilesUtil.readResourceFile(SchemaBuilder.DROP_SCRIPT_FILE_NAME);
	}

	/**
	 * execute the schemaCreationScript using JDBI library.
	 */
	public void createTables() {
		final DBI dbi = LocalRegistry.getDbi();
		final Handle handle = dbi.open();
		try {
			handle.execute(this.schemaCreationScript);
		} finally {
			handle.close();
		}
	}

	/**
	 * execute the schemaDroppScript using JDBI library.
	 */
	public void dropTables() {
		final DBI dbi = LocalRegistry.getDbi();
		final Handle handle = dbi.open();
		try {
			handle.execute(this.schemaDroppScript);
		} finally {
			handle.close();
		}
	}

}
