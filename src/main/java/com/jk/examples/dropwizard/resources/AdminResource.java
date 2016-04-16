package com.jk.examples.dropwizard.resources;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jk.examples.dropwizard.util.Messages;
import com.jk.examples.dropwizard.util.SchemaBuilder;

/**
 * This service is used for Administration purposes , it contains the following actoins : 
 * 1- Create Database Schema
 * 2- Drop Database Schema
 * 3- Clean Database Schema
 * 
 * @author Jalal Kiswani
 *
 *Note  : Some method shold be called as POST ot GET , but i have made all the exposed services in this
 * Resource called using HTTP Get method for simpler testing prupose by hitting browser URL only.
 */
@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
public class AdminResource {
	/**
	 * instance Schema Builder class which is used to manage the databse structor functianlity
	 */
	private SchemaBuilder builder;

	/**
	 * Default Constructor
	 * @throws IOException
	 */
	public AdminResource() throws IOException {
		builder = new SchemaBuilder();
	}

	/**
	 * Service to create the In-Memory database structor 
	 * @return
	 */
	@Path("db/create")
	@GET
	public String createDatabase() {
		builder.createTables();
		return Messages.get("Database created succ");
	}

	/**
	 * Service to drop the Tables if exists
	 * @return
	 */
	@Path("db/drop")
	@GET
	public String dropTables() {
		builder.dropTables();
		return Messages.get("Database tables dropped succ");
	}

	/**
	 * Clean the database by calling the dropTables method followed by createDatabase method
	 * @return
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

}
