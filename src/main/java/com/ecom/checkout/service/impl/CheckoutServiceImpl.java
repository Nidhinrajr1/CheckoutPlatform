package com.ecom.checkout.service.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.checkout.model.Cart;
import com.ecom.checkout.model.Offer;
import com.ecom.checkout.service.CheckoutService;
import com.ecom.checkout.service.OfferService;
import com.ecom.checkout.service.WatchService;

/**
 * @author nidhi
 *
 *         Service implementation for checkout operation *
 */
@Service
public class CheckoutServiceImpl implements CheckoutService {

	private static final Logger LOGGER = LogManager.getLogger();

	@Autowired
	private OfferService offerService;

	@Autowired
	private WatchService watchService;

	/**
	 * Method to perform checkout operation. Return final price after applying
	 * offers.
	 * 
	 * @param cart
	 * @return Total price for the watches in the cart
	 */
	@Override
	public Double checkout(Cart cart) {
		Double finalPrice = 0.0;
		LOGGER.info("Inside checkout service");
		try {

			// List of watches in cart
			List<String> watchIds = cart.getItems();

			// Unique watches in the cart
			Set<String> watchSet = new HashSet<String>(watchIds);

			for (String watchId : watchSet) {
				// Fetching the offers available for watch
				Offer offer = offerService.getOffer(watchId);

				if (offer != null) {
					finalPrice += applyOffer(watchIds, offer);
				} else {
					finalPrice += watchService.getWatch(watchId).getPrice();
				}

			}

		} catch (Exception e) {
			LOGGER.error("Exception occured while applying offer", e);
		}

		return finalPrice;
	}

	/**
	 * Method for calculating final price of watches after applying available offer.
	 * 
	 * @param watchIds
	 * @param offer
	 * @return
	 */
	private Double applyOffer(List<String> watchIds, Offer offer) {
		Double offerPrice = 0.0;
		try {
			int selectedWatches = Collections.frequency(watchIds, offer.getWatchId());
			int offerCount = offer.getCount();

			// Count of offer applied
			int offerApplied = selectedWatches / offerCount;

			int remainingCount = selectedWatches % offerCount;

			offerPrice = offerApplied * offer.getOfferPrice()
					+ remainingCount * watchService.getWatch(offer.getWatchId()).getPrice();

		} catch (Exception e) {
			LOGGER.error("Exception occured while applying offer", e);
		}
		return offerPrice;
	}

}
