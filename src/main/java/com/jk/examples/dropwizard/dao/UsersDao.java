package com.jk.examples.dropwizard.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import com.jk.examples.dropwizard.beans.User;
import com.jk.examples.dropwizard.beans.UserVisitLog;

/**
 * This class is the DataBase handler for the Users and UserVisitsLog beans.
 * This class is currently handling all the required method to deal with tc_users & tx_visits_log 
 * table in the Database.
 * @author Jalal Kiswani
 *
 */
public interface UsersDao {

	/**
	 * Add new user to tc_users table
	 * @param user
	 */
	@SqlUpdate("INSERT  INTO tc_users(name) VALUES (:name)")
	public void addUser(@BindBean User user);

	/**
	 * Add new visit record to the tc_visits_log table
	 * @param log
	 */
	@SqlUpdate("INSERT INTO tc_visits_log(visitor_user_id,visited_user_id,visit_time_stamp) VALUES (:visitorUserId,:visitedUserId,:timeStamp)")
	public void recordVisitLog(@BindBean UserVisitLog log);

	/**
	 * Get the last 10 visitors for the user with given id. 
	 * This method is return the top 10 results from the database after it sort the results by date descending
	 * @param id
	 * @return
	 */
	@SqlQuery("SELECT * FROM  tc_visits_log WHERE visited_user_id=:id ORDER BY visit_time_stamp DESC LIMIT 10")
	public List<UserVisitLog> getLastVisitors(@Bind("id") int id);

	/**
	 * Get all the visitors record to given user ordered descending by visit date
	 * @param id
	 * @return
	 */
	@SqlQuery("SELECT * FROM  tc_visits_log WHERE visited_user_id=:id ORDER BY visit_time_stamp DESC")
	public List<UserVisitLog> getAllVisitors(@Bind("id") int id);

	/**
	 * Find user object by the given user id
	 * @param id
	 * @return
	 */
	@SqlQuery("SELECT * FROM tc_users WHERE id=:id")
	public User findUser(@Bind int id);

	/**
	 * Close the database resource , this method is automatically called by the JDBI library
	 */
	public void close();

}