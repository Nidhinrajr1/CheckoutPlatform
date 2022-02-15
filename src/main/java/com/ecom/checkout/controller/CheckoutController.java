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
	 * @param ids
	 * 
	 * @return
	 */
	@PostMapping("/checkout")
	public @ResponseBody Map<String, Integer> checkout(@RequestBody String[] ids) {
		Map<String, Integer> priceMap = new HashMap<String, Integer>();
		System.out.println("Input Watch ids================"+ Arrays.toString(ids));
		try {

			LOGGER.debug("Input Watch ids", Arrays.toString(ids));
			
			/* if (ids != null && ids.length > 0) { */
				Cart cart = new Cart();
				cart.setItems(Arrays.asList(ids));

				priceMap = checkoutService.checkout(cart);
			/*}
			else {
				LOGGER.error("EMPTY!!!!!!!!!!!!!!!!!!");
			}*/
				priceMap.put("price", 360);
				
		} catch (Exception ex) {
			LOGGER.error("Exception occured in checkout", ex);
		}
		return priceMap;
	}

}
