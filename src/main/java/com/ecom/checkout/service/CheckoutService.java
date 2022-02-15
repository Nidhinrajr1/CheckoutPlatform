package com.ecom.checkout.service;

import java.util.Map;

import com.ecom.checkout.model.Cart;


public interface CheckoutService {

	 Map<String,Integer> checkout(Cart cart);

}
