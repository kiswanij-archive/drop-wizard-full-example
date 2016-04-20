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
package com.jk.examples.dropwizard.util;

/**
 * This class should manage the Localizatio in the future for this service.
 *
 * @author Jalal Kiswani
 */
public class Messages {

	/**
	 * This method return the lable for the given key , currently its return the
	 * key as is.
	 *
	 * @param key
	 *            the key
	 * @return the string
	 */
	public static String get(final String key) {
		return key;
	}

}
