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
	/**
	 * Id field
	 */
	private int id;

	/**
	 * Visitor field
	 */
	private User visitor;

	/**
	 * Visited User field
	 */
	private User visitedUser;

	/**
	 * Visit time stamp (Date & Time)
	 */
	private Timestamp timeStamp;

	/**
	 * Default Constructor
	 */
	public UserVisitLog() {
	}

	/**
	 * Fully loaded constructor
	 * 
	 * @param visitorId
	 * @param visitedId
	 * @param timeStamp
	 */
	public UserVisitLog(int visitorId, int visitedId, Timestamp timeStamp) {
		setVisitor(new User(visitorId));
		setVisitedUser(new User(visitedId));
		setTimeStamp(timeStamp);
	}

	/**
	 * Getter for the ID field
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter for the ID field
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter for Visitor field
	 * 
	 * @return
	 */
	public User getVisitor() {
		return visitor;
	}

	/**
	 * Setter for the Visitor field
	 * 
	 * @param visitor
	 */
	public void setVisitor(User visitor) {
		this.visitor = visitor;
	}

	/**
	 * Getter for Visited User field
	 * 
	 * @return
	 */
	public User getVisitedUser() {
		return visitedUser;
	}

	/**
	 * Setter for Visited user field
	 * 
	 * @param visitedUser
	 */
	public void setVisitedUser(User visitedUser) {
		this.visitedUser = visitedUser;
	}

	/**
	 * Getter for timestamp field
	 * 
	 * @return
	 */
	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	/**
	 * Setter for the timeStamp field
	 * 
	 * @param timeStamp
	 */
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * Wrapper method to get the Visitor user id directly from the visitor
	 * object.
	 * 
	 * @return
	 */
	public int getVisitorUserId() {
		return getVisitor().getId();
	}

	/**
	 * Wrapper method to get the visited user id directly from the visited user
	 * object.
	 * 
	 * @return
	 */
	public int getVisitedUserId() {
		return getVisitedUser().getId();
	}

	/**
	 * Map method to be used from the JDBI library
	 */
	public UserVisitLog map(int i, ResultSet rs, StatementContext context) throws SQLException {
		UserVisitLog log = new UserVisitLog();
		log.setId(rs.getInt("id"));
		log.setVisitor(new User(rs.getInt("visitor_user_id")));
		log.setVisitedUser(new User(rs.getInt("visited_user_id")));
		log.setTimeStamp(rs.getTimestamp("visit_time_stamp"));
		return log;
	}

	/**
	 * This method checks if this visit is expired . The Page is considered
	 * expired if its recorded for more than {VisitListMaxDays} variable which
	 * is defined in AssignmentConfigurations#getVisitListMaxDays() method
	 * 
	 * @return wheather outdated or not
	 */
	public boolean isOutDated() {
		DateTime timeStamp = new DateTime(getTimeStamp());
		DateTime expiryDate = timeStamp.plusDays(LocalRegistry.getConfigurations().getVisitListMaxDays());

		DateTime now = new DateTime();
		return now.isAfter(expiryDate);
	}

	/**
	 * Convert this object to helpfull String representation
	 */
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer("Log[");
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
