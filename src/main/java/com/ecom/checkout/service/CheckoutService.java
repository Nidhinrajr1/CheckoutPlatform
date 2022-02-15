package com.ecom.checkout.service;

import com.ecom.checkout.model.Cart;

public interface CheckoutService {

	/**
	 * Method to perform checkout operation. Return final price after applying
	 * offers.
	 * 
	 * @param cart
	 * @return
	 */
	Double checkout(Cart cart);

}
