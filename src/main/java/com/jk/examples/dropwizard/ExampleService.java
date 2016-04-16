package com.jk.examples.dropwizard;

import org.skife.jdbi.v2.DBI;

import com.jk.examples.dropwizard.beans.User;
import com.jk.examples.dropwizard.beans.UserVisitLog;
import com.jk.examples.dropwizard.health.ExampleServiceHealthCheck;
import com.jk.examples.dropwizard.resources.AdminResource;
import com.jk.examples.dropwizard.resources.TestResource;
import com.jk.examples.dropwizard.resources.UserResource;
import com.jk.examples.dropwizard.util.LocalRegistry;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.jdbi.DBIFactory;

/**
 * This is the kitchen where every thing is cooked up and prepared.
 * @author Jalal Kiswani
 *
 */
public class ExampleService extends Service<ExampleConfigurations> {

	/**
	 * This method is the called in the Service bootstrap phase  , we use it
	 * only to set the service name
	 */
	@Override
	public void initialize(Bootstrap<ExampleConfigurations> bootstrap) {
		bootstrap.setName("truecaller-assignment");
	}

	/**
	 * The service startup method , we use it register the resources , and to init the LocalRegistry
	 */
	@Override
	public void run(ExampleConfigurations configuration, Environment environment) throws Exception {
		register(configuration, environment);

		environment.addResource(new AdminResource());
		environment.addResource(new TestResource());
		environment.addResource(new UserResource());
		environment.addHealthCheck(new ExampleServiceHealthCheck());
	}

	/**
	 * Init the local registry and the JDBI mappers
	 * @param configuration
	 * @param environment
	 * @throws ClassNotFoundException
	 */
	private void register(ExampleConfigurations configuration, Environment environment) throws ClassNotFoundException {
		DBIFactory factory = new DBIFactory();

		DBI dbi = factory.build(environment, configuration.getDatabase(), "h2");
		dbi.registerMapper(new User());
		dbi.registerMapper(new UserVisitLog());

		LocalRegistry.setDbi(dbi);
		LocalRegistry.setEnvironment(environment);
		LocalRegistry.setConfigurations(configuration);
	}

	/**
	 * Main Method to start the application
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		new ExampleService().run(args);
	}

}
