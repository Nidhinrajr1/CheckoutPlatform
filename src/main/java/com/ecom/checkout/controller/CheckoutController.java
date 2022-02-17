package com.ecom.checkout.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.checkout.model.Cart;
import com.ecom.checkout.service.CheckoutService;

@RestController
public class CheckoutController {

	private static final Logger LOGGER = LogManager.getLogger();

	@Autowired
	private CheckoutService checkoutService;

	/**
	 * Method to checkout watches. This method will return total checkout price of
	 * list of selected watches
	 * 
	 * @param watchIds
	 * @return Return map contain total price of watches.
	 */
	@PostMapping("/checkout")
	public @ResponseBody Map<String, Integer> checkout(@RequestBody String[] watchIds) {
		Map<String, Integer> responseMap = new HashMap<String, Integer>();
		try {

			LOGGER.debug("Input Watch ids", Arrays.toString(watchIds));

			if (watchIds != null && watchIds.length > 0) {
				Cart cart = new Cart();
				cart.setItems(Arrays.asList(watchIds));

				Double price = checkoutService.checkout(cart);
				responseMap.put("price", price.intValue());

			} else {
				LOGGER.warn("Empty input watchIds");
				responseMap.put("price", 0);
			}

		} catch (Exception ex) {
			LOGGER.error("Exception occured in checkout", ex);
		}
		return responseMap;
	}

}
