package com.ecom.checkout.util;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

public class FileUtil {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	public static File getJsonFile(String fileName) throws FileNotFoundException {

		if (StringUtils.isEmpty(fileName)) {
			throw new FileNotFoundException("Invalid file name");
		}
		File file = ResourceUtils.getFile(fileName);
		return file;
	}
}
