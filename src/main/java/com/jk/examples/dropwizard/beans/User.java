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
package com.jk.examples.dropwizard.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * This class encapsulates the User info. It contains the user id and user name
 * fields. Also , it implements the ResultSetMapper class and override the map
 * method to be able used as Mapper with JDBI library for simpler Database
 * mapping
 *
 * @author Jalal H.Kiswani
 *
 */
public class User implements ResultSetMapper<User> {
	/**
	 * Id field
	 */
	private int id;

	/**
	 * Name field
	 */
	private String name;

	/**
	 * Default Constructor
	 */
	public User() {
	}

	/**
	 * Constructor with Id parameter
	 * 
	 * @param id
	 */
	public User(final int id) {
		setId(id);
	}

	/**
	 * Constructor that take id and name as paramters
	 * 
	 * @param id
	 * @param name
	 */
	public User(final int id, final String name) {
		setId(id);
		setName(name);
	}

	/**
	 * Constructor with name parameter
	 * 
	 * @param name
	 */
	public User(final String name) {
		this.name = name;
	}

	/**
	 * Getter for the ID field
	 * 
	 * @return
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Getter for the Name field
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Map method to be used by the JDBI library
	 */
	public User map(final int i, final ResultSet rs, final StatementContext context) throws SQLException {
		final User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		return user;
	}

	/**
	 * Setter for the ID field
	 * 
	 * @param id
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * setter for the Name field
	 * 
	 * @param name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Create String represntatoin for this instance , which will be in
	 * "User ({id})" format
	 */
	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("User (");
		buffer.append(getId());
		buffer.append(")");
		return buffer.toString();
	}
}
