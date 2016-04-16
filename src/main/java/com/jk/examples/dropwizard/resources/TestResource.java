package com.jk.examples.dropwizard.resources;

import java.sql.Timestamp;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.jboss.logging.Message;
import org.joda.time.DateTime;

import com.jk.examples.dropwizard.beans.User;
import com.jk.examples.dropwizard.beans.UserVisitLog;
import com.jk.examples.dropwizard.facade.UsersFacade;
import com.jk.examples.dropwizard.util.Messages;
import com.yammer.metrics.annotation.Timed;

/**
 * This service is used to create the required data for the testing
 * 
 * Note : Some method shold be called as POST ot GET , but i have made all the
 * exposed services in this Resource called using HTTP Get method for simpler
 * testing prupose by hitting browser URL only.
 * 
 * @author Jalal Kiswani
 * 
 * Note  : Some method shold be called as POST ot GET , but i have made all the exposed services in this
 * Resource called using HTTP Get method for simpler testing prupose by hitting browser URL only.
 */
@Path("/test")
public class TestResource {
	/**
	 * This method is very usefull for the testing , it fill the tc_users table
	 * by {users_count} records , then it create {visits_per_user} for {days}
	 * 
	 * @param usersCount
	 * @param days
	 * @param visits
	 * @return
	 */
	@GET
	@Timed
	@Path("create/users/{users_count}/{days}/{visits_per_user}")
	public String addTestRecords(@PathParam("users_count") int usersCount, @PathParam("days") int days, @PathParam("visits_per_user") int visits) {
		UsersFacade facade = UsersFacade.getInstance();
		System.out.println("Creating records for :" + usersCount + " users");
		for (int i = 1; i <= usersCount; i++) {
			User user = new User(i, "User " + i);
			facade.addUser(user);
		}
		// create {visits_per_user} visits to each user
		Random rand = new Random();
		System.out.println("Creating " + visits + " records for all the users for :" + days + " days");
		for (int i = 1; i <= usersCount; i++) {
			System.out.println("Creating Random Visits for User :" + i);
			for (int j = 0; j < visits; j++) {
				DateTime time = new DateTime();
				int randomDays = rand.nextInt(days) + 1;
				//shift time back by randomDays value
				time = time.minusDays(randomDays);
				
				//random visitor id
				int randomVisitor = rand.nextInt(usersCount) + 1;
				
				//create log object
				UserVisitLog log = new UserVisitLog(randomVisitor, i, new Timestamp(time.getMillis()));
				
				//save log object ASYNCH to the database
				facade.recordVisitLog(log);
			}
		}

		return Messages.get(usersCount + " Users added Succ");
	}
}
