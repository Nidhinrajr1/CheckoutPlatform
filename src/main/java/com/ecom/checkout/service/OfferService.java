package com.ecom.checkout.service;

import com.ecom.checkout.model.Offer;

public interface OfferService {
	
	/**
	 * @return
	 */
	Offer getOffer(String watchId);
	
}
