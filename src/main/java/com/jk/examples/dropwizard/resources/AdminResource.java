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
package com.jk.examples.dropwizard.resources;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jk.examples.dropwizard.util.Messages;
import com.jk.examples.dropwizard.util.SchemaBuilder;

/**
 * This service is used for Administration purposes , it contains the following
 * actoins : 1- Create Database Schema 2- Drop Database Schema 3- Clean Database
 * Schema.
 *
 * @author Jalal Kiswani
 *
 *         Note : Some method shold be called as POST ot GET , but i have made
 *         all the exposed services in this Resource called using HTTP Get
 *         method for simpler testing prupose by hitting browser URL only.
 */
@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
public class AdminResource {

	/**
	 * instance Schema Builder class which is used to manage the databse
	 * structor functianlity.
	 */
	private final SchemaBuilder builder;

	/**
	 * Default Constructor.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public AdminResource() throws IOException {
		this.builder = new SchemaBuilder();
	}

	/**
	 * Clean the database by calling the dropTables method followed by
	 * createDatabase method.
	 *
	 * @return the string
	 */
	@Path("db/clean")
	@GET
	public String cleanDatabase() {
		System.out.print("Dropping Tables if Exists");
		dropTables();
		System.out.println("Creating Tables");
		createDatabase();
		return Messages.get("Database cleaned-up succ");
	}

	/**
	 * Service to create the In-Memory database structor.
	 *
	 * @return the string
	 */
	@Path("db/create")
	@GET
	public String createDatabase() {
		this.builder.createTables();
		return Messages.get("Database created succ");
	}

	/**
	 * Service to drop the Tables if exists.
	 *
	 * @return the string
	 */
	@Path("db/drop")
	@GET
	public String dropTables() {
		this.builder.dropTables();
		return Messages.get("Database tables dropped succ");
	}

}
