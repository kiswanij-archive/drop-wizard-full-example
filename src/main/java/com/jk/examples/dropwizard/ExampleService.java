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
 * 
 * @author Jalal Kiswani
 *
 */
public class ExampleService extends Service<ExampleConfigurations> {

	/**
	 * Main Method to start the application.
	 *
	 * @param args
	 *            the arguments
	 * @throws Exception
	 *             the exception
	 */
	public static void main(final String[] args) throws Exception {
		new ExampleService().run(args);
	}

	/**
	 * This method is the called in the Service bootstrap phase , we use it only
	 * to set the service name.
	 *
	 * @param bootstrap
	 *            the bootstrap
	 */
	@Override
	public void initialize(final Bootstrap<ExampleConfigurations> bootstrap) {
		bootstrap.setName("truecaller-assignment");
	}

	/**
	 * Init the local registry and the JDBI mappers.
	 *
	 * @param configuration
	 *            the configuration
	 * @param environment
	 *            the environment
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 */
	private void register(final ExampleConfigurations configuration, final Environment environment)
			throws ClassNotFoundException {
		final DBIFactory factory = new DBIFactory();

		final DBI dbi = factory.build(environment, configuration.getDatabase(), "h2");
		dbi.registerMapper(new User());
		dbi.registerMapper(new UserVisitLog());

		LocalRegistry.setDbi(dbi);
		LocalRegistry.setEnvironment(environment);
		LocalRegistry.setConfigurations(configuration);
	}

	/**
	 * The service startup method , we use it register the resources , and to
	 * init the LocalRegistry.
	 *
	 * @param configuration
	 *            the configuration
	 * @param environment
	 *            the environment
	 * @throws Exception
	 *             the exception
	 */
	@Override
	public void run(final ExampleConfigurations configuration, final Environment environment) throws Exception {
		register(configuration, environment);

		environment.addResource(new AdminResource());
		environment.addResource(new TestResource());
		environment.addResource(new UserResource());
		environment.addHealthCheck(new ExampleServiceHealthCheck());
	}

}
