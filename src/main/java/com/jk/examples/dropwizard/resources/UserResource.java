package com.jk.examples.dropwizard.resources;

import java.sql.Timestamp;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jk.examples.dropwizard.beans.UserVisitLog;
import com.jk.examples.dropwizard.facade.UsersFacade;
import com.jk.examples.dropwizard.util.LocalRegistry;
import com.jk.examples.dropwizard.util.Messages;
import com.yammer.metrics.annotation.Timed;

/**
 * This is the core Bussiness Service required by the assignment , contains :
 * 1- recordVisitLog service "visits/log/{visitor_id}/{visited_id}"
 * 2- getLastVisitors service "visitors/{user}"
 * 3- getAllVisitors  all-visitors/{user}
 * 
 * @author Jalal Kiswani
 * 
 * Note  : Some method shold be called as POST ot GET , but i have made all the exposed services in this
 * Resource called using HTTP Get method for simpler testing prupose by hitting browser URL only.
 *
 */
@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	/**
	 * Instance of UsersFacade
	 */
	private UsersFacade facade = UsersFacade.getInstance();

	/**
	 * Default Constrcutor
	 */
	public UserResource() {
	}

	/**
	 * Add new users visit log record 
	 * @param visitorId
	 * @param visitedId
	 * @return
	 */
	@GET
	@Timed
	@Path("visits/log/{visitor_id}/{visited_id}")
	public Response recordVisitLog(@PathParam("visitor_id") int visitorId, @PathParam("visited_id") int visitedId) {
		facade.recordVisitLog(new UserVisitLog(visitorId, visitedId, new Timestamp(System.currentTimeMillis())));
		return Response.ok(Messages.get("Record Added Succ")).build();
	}

	/**
	 * Get String contains the last 10 valid visits for the given user.
	 * The visit is considered value if its not outDated(not registred before 10 Days)
	 * @param userId
	 * @return
	 */
	@GET
	@Timed
	@Path("visitors/{user}")
	public String getLastVisitors(@PathParam("user") int userId) {
		List<UserVisitLog> lastVisitors = facade.getLastVisitors(userId);
		return format(lastVisitors);
	}

	/**
	 * Get list of all the user visits
	 * @param userId
	 * @return
	 */
	@GET
	@Timed
	@Path("all-visitors/{user}")
	public String getAllVisitors(@PathParam("user") int userId) {
		List<UserVisitLog> allVisitors = facade.getAllVisitors(userId);
		return format(allVisitors);
	}

	/**
	 * Format the giev List to readable String , which is row on every line 
	 * e.g.(Log[#{log_id},User ({visited_user_id}),User ({visitor_user_id}),{time_stamp},{out_dated}])
	 * @param allVisitors
	 * @return
	 */
	private String format(List<UserVisitLog> allVisitors) {
		String separtor = LocalRegistry.getConfigurations().getOutputSeprator();

		StringBuffer buf = new StringBuffer();
		for (UserVisitLog userVisitLog : allVisitors) {
			buf.append(userVisitLog.toString()).append(separtor);
		}

		return buf.toString();
	}

}
