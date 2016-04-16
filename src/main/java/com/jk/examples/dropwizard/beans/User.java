package com.jk.examples.dropwizard.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * This class encapsulates the User info.
 * It contains the user id and user name fields.
 * Also , it implements the ResultSetMapper class and override 
 * the map method to be able used as 
 * Mapper with JDBI library for simpler Database mapping
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
	 * Constructor with name parameter
	 * @param name
	 */
	public User(String name) {
		this.name = name;
	}

	/**
	 * Constructor with Id parameter
	 * @param id
	 */
	public User(int id) {
		setId(id);
	}

	/**
	 * Constructor that take id and name as paramters
	 * @param id
	 * @param name
	 */
	public User(int id, String name) {
		setId(id);
		setName(name);
	}

	/**
	 * Getter for the ID field
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter for the ID field
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter for the Name field
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter for the Name field
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Map method to be used by the JDBI library
	 */
	public User map(int i, ResultSet rs, StatementContext context) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		return user;
	}

	/**
	 * Create String represntatoin for this instance , which will be in "User ({id})" format
	 */
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("User (");
		buffer.append(getId());
		buffer.append(")");
		return buffer.toString();
	}
}
