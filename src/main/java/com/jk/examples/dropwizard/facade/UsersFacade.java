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
package com.jk.examples.dropwizard.facade;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.jk.examples.dropwizard.beans.User;
import com.jk.examples.dropwizard.beans.UserVisitLog;
import com.jk.examples.dropwizard.dao.UsersDao;
import com.jk.examples.dropwizard.util.LocalRegistry;

/**
 * This class is the Interface for any bussiness call required regarding our
 * service, It aims to hide any complexity for the front-end ,service or
 * resource classes . We made this class as singleton because it contains Thread
 * Pool and we want to be sure that this pool is the not duplicated by any other
 * instance.
 *
 * @author Jalal Kiswani
 *
 */
public class UsersFacade {
	/**
	 * Singleton instance of this class
	 */
	private static UsersFacade instance;

	/**
	 * Singleton instance getter of this class. Also , this method is the first
	 * place where the instance in constructor in the first call
	 * 
	 * @return
	 */
	public static UsersFacade getInstance() {
		if (UsersFacade.instance == null) {
			UsersFacade.instance = new UsersFacade();
		}
		return UsersFacade.instance;
	}

	/**
	 * instance of users dao
	 */
	private final UsersDao dao;

	/**
	 * Thread Pool to be used executing the recordVisitLog to avoid any overhead
	 * on the server and to avoid any delay in the user experience
	 */
	ExecutorService pool;

	/**
	 * Private construtor , this method init the pool and the users dao
	 */
	private UsersFacade() {
		this.dao = LocalRegistry.getDbi().onDemand(UsersDao.class);
		this.pool = Executors.newFixedThreadPool(LocalRegistry.getConfigurations().getDatabaseThreadsPoolSize());
	}

	/**
	 * Wrapper method to add the user to the database by calling dao.addUser
	 * 
	 * @param user
	 */
	public void addUser(final User user) {
		this.dao.addUser(user);
	}

	/**
	 * Wrapper method for finding a user by id , it calls dao.findUser
	 * 
	 * @param userId
	 * @return
	 */
	public User findUser(final int userId) {
		return this.dao.findUser(userId);
	}

	/**
	 * Wrapper method to get all the visitors for given use id
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserVisitLog> getAllVisitors(final int userId) {
		return this.dao.getAllVisitors(userId);
	}

	/**
	 * Get the list of the visits for given user . This method call the
	 * getLastVisitor method on the UsersDao which returns the last 10 visits
	 * for this user , then it remove the out dated logs . It checks for the
	 * outdated state by calling log.isOutDated()
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserVisitLog> getLastVisitors(final int userId) {
		final List<UserVisitLog> visitors = this.dao.getLastVisitors(userId);
		for (int i = 0; i < visitors.size(); i++) {
			final UserVisitLog log = visitors.get(i);
			if (log.isOutDated()) {
				visitors.remove(i--);
			}

		}
		return visitors;
	}

	/**
	 * This method records the visit log for a user. This method is called from
	 * within the thread pool for better performance
	 * 
	 * @param log
	 */
	public void recordVisitLog(final UserVisitLog log) {
		final Runnable runnable = new Runnable() {
			public void run() {
				UsersFacade.this.dao.recordVisitLog(log);
			}
		};
		this.pool.execute(runnable);
	}

}
