package com.ecom.checkout.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ecom.checkout.model.Offer;
import com.ecom.checkout.service.OfferService;
import com.ecom.checkout.util.FileUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OfferServiceImpl implements OfferService {

	private static final Logger LOGGER = LogManager.getLogger();

	private static Map<String, Offer> offers = new HashMap<String, Offer>();;

	/**
	 * Method for fetching available offer for a watch
	 *
	 * @return Offer
	 */
	@Override
	public Offer getOffer(String watchId) {
		try {
			offers = getOffers();
			return offers.get(watchId);
		} catch (Exception e) {
			LOGGER.error("Exception while fetching offer", e);
		}
		return null;

	}

	/**
	 * Method for loading offers from json file
	 * 
	 * @return
	 */
	public Map<String, Offer> getOffers() {

		try {
			if (offers.isEmpty()) {
				File file = FileUtil.getJsonFile("classpath:offer.json");

				ObjectMapper objectMapper = new ObjectMapper();

				Offer[] offerArray = objectMapper.readValue(file, Offer[].class);
				for (Offer offer : offerArray) {

					offers.put(offer.getWatchId(), offer);
				}
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return offers;
	}

}
