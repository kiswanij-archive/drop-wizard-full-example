package com.jk.examples.dropwizard.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;


/**
 * Utility methods to deal with Files
 * @author Jalal
 *
 */
public class FilesUtil {

	/**
	 * Read files contents as string using Apache IO library,
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String readResourceFile(String fileName) throws IOException {
		InputStream in = null;
		try {
			in = FilesUtil.class.getResourceAsStream(fileName);
			if(in==null){
				System.out.print("Resource "+fileName+" is not found");
				return null;
			}
			return IOUtils.toString(in);
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

}
