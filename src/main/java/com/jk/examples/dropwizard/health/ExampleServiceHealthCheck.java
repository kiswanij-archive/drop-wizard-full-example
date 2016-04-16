package com.jk.examples.dropwizard.health;

import com.yammer.metrics.core.HealthCheck;

/**
 * HealthCheck implmenetation based on DropWhizard recommandation , 
 * howvere , this implementation is still empty , and to be completed in 
 * the future if needed 
 * @author Jalal Kiswani
 *
 */
public class ExampleServiceHealthCheck extends HealthCheck{

	/**
	 * Default Constrcutor
	 */
	public ExampleServiceHealthCheck() {
		super("Users Health Check");
	}

	@Override
	protected Result check() throws Exception {
		// TODO : complete the health checks
		return Result.healthy();
	}

}
