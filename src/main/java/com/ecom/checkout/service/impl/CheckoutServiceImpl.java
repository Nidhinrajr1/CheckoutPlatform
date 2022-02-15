package com.ecom.checkout.service.impl;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ecom.checkout.model.Cart;
import com.ecom.checkout.service.CheckoutService;

@Service
public class CheckoutServiceImpl implements CheckoutService {

	private static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Map<String, Integer> checkout(Cart cart) {
		LOGGER.info("Inside checkout service");

		return null;
	}

}
