package com.ecom.checkout.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ecom.checkout.model.Watch;
import com.ecom.checkout.service.WatchService;
import com.ecom.checkout.util.FileUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author nidhi
 *
 */
@Service
public class WatchServiceImpl implements WatchService {

	private static final Logger LOGGER = LogManager.getLogger();

	private static Map<String, Watch> watches = new HashMap<String, Watch>();

	@Override
	public Watch getWatch(String watchId) {
		try {
			watches = getWatches();
			return watches.get(watchId);
		} catch (Exception e) {
			LOGGER.error("Exception while fetching offer", e);
		}
		return null;
	}

	/**
	 * Method for loading watches from json file
	 * 
	 * @return Map with watchId as key and watch as value
	 */
	private Map<String, Watch> getWatches() {

		try {
			if (watches.isEmpty()) {
				File file = FileUtil.getJsonFile("classpath:watches.json");

				ObjectMapper objectMapper = new ObjectMapper();

				Watch[] watcheArray = objectMapper.readValue(file, Watch[].class);
				for (Watch watch : watcheArray) {

					watches.put(watch.getId(), watch);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return watches;
	}
}
