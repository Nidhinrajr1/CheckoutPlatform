package com.ecom.checkout.model;

import java.io.Serializable;

/**
 * Model class for offers available for different watches.
 * 
 * @author nidhi
 *
 */
public class Offer implements Serializable {

	private static final long serialVersionUID = 1L;

	/** watch id which have offer */
	private String watchId;

	/** Number of watches for price discount */
	private int count;

	/** Discount price for set of watches */
	private Double offerPrice;

	public String getWatchId() {
		return watchId;
	}

	public void setWatchId(String watchId) {
		this.watchId = watchId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Double getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(Double offerPrice) {
		this.offerPrice = offerPrice;
	}

}
