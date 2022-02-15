package com.ecom.checkout.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ecom.checkout.model.Cart;
import com.ecom.checkout.service.CheckoutService;

@Service
public class CheckoutServiceImpl implements CheckoutService {

	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Method to perform checkout operation. Return final price after applying
	 * offers.
	 * 
	 * @param cart
	 * @return
	 * 		Total price for the watches in the cart
	 */
	@Override
	public Double checkout(Cart cart) {
		LOGGER.info("Inside checkout service");
		try {
			

			
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

}
