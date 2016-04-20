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
import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.jk.examples.dropwizard.util.LocalRegistry;

/**
 * This class encapsulates the Users Visits info , it contains the Id , Visitor
 * , Visited and visit time stamp. This class implements the ResultSetMapper
 * interface to be usefull when used with the JDBI library
 *
 * @author Jalal Kiswani
 *
 */
public class UserVisitLog implements ResultSetMapper<UserVisitLog> {
	
	/** Id field. */
	private int id;

	/** Visitor field. */
	private User visitor;

	/** Visited User field. */
	private User visitedUser;

	/** Visit time stamp (Date & Time). */
	private Timestamp timeStamp;

	/**
	 * Default Constructor.
	 */
	public UserVisitLog() {
	}

	/**
	 * Fully loaded constructor.
	 *
	 * @param visitorId
	 *            the visitor id
	 * @param visitedId
	 *            the visited id
	 * @param timeStamp
	 *            the time stamp
	 */
	public UserVisitLog(final int visitorId, final int visitedId, final Timestamp timeStamp) {
		setVisitor(new User(visitorId));
		setVisitedUser(new User(visitedId));
		setTimeStamp(timeStamp);
	}

	/**
	 * Getter for the ID field.
	 *
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Getter for timestamp field.
	 *
	 * @return the time stamp
	 */
	public Timestamp getTimeStamp() {
		return this.timeStamp;
	}

	/**
	 * Getter for Visited User field.
	 *
	 * @return the visited user
	 */
	public User getVisitedUser() {
		return this.visitedUser;
	}

	/**
	 * Wrapper method to get the visited user id directly from the visited user
	 * object.
	 *
	 * @return the visited user id
	 */
	public int getVisitedUserId() {
		return getVisitedUser().getId();
	}

	/**
	 * Getter for Visitor field.
	 *
	 * @return the visitor
	 */
	public User getVisitor() {
		return this.visitor;
	}

	/**
	 * Wrapper method to get the Visitor user id directly from the visitor
	 * object.
	 *
	 * @return the visitor user id
	 */
	public int getVisitorUserId() {
		return getVisitor().getId();
	}

	/**
	 * This method checks if this visit is expired . The Page is considered
	 * expired if its recorded for more than {VisitListMaxDays} variable which
	 * is defined in AssignmentConfigurations#getVisitListMaxDays() method
	 *
	 * @return wheather outdated or not
	 */
	public boolean isOutDated() {
		final DateTime timeStamp = new DateTime(getTimeStamp());
		final DateTime expiryDate = timeStamp.plusDays(LocalRegistry.getConfigurations().getVisitListMaxDays());

		final DateTime now = new DateTime();
		return now.isAfter(expiryDate);
	}

	/**
	 * Map method to be used from the JDBI library.
	 *
	 * @param i
	 *            the i
	 * @param rs
	 *            the rs
	 * @param context
	 *            the context
	 * @return the user visit log
	 * @throws SQLException
	 *             the SQL exception
	 */
	public UserVisitLog map(final int i, final ResultSet rs, final StatementContext context) throws SQLException {
		final UserVisitLog log = new UserVisitLog();
		log.setId(rs.getInt("id"));
		log.setVisitor(new User(rs.getInt("visitor_user_id")));
		log.setVisitedUser(new User(rs.getInt("visited_user_id")));
		log.setTimeStamp(rs.getTimestamp("visit_time_stamp"));
		return log;
	}

	/**
	 * Setter for the ID field.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * Setter for the timeStamp field.
	 *
	 * @param timeStamp
	 *            the new time stamp
	 */
	public void setTimeStamp(final Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * Setter for Visited user field.
	 *
	 * @param visitedUser
	 *            the new visited user
	 */
	public void setVisitedUser(final User visitedUser) {
		this.visitedUser = visitedUser;
	}

	/**
	 * Setter for the Visitor field.
	 *
	 * @param visitor
	 *            the new visitor
	 */
	public void setVisitor(final User visitor) {
		this.visitor = visitor;
	}

	/**
	 * Convert this object to helpfull String representation.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		final StringBuffer buf = new StringBuffer("Log[");
		buf.append(getId());
		buf.append(",");
		buf.append(getVisitedUser());
		buf.append(",");
		buf.append(getVisitor());
		buf.append(",");
		buf.append(getTimeStamp());
		buf.append(",");
		buf.append(isOutDated());
		buf.append("]");
		return buf.toString();
	}

}
