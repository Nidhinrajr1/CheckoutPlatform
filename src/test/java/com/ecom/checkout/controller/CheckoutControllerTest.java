package com.ecom.checkout.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ecom.checkout.model.Cart;
import com.ecom.checkout.service.CheckoutService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CheckoutController.class)
public class CheckoutControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CheckoutService checkoutService;

	/**
	 * Checking for total price after applying offer.
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkoutShouldGetPriceFromService() throws Exception {
		String[] watchIds = new String[] { "001", "002", "001", "001", "004", "003" };
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonRequest = objectMapper.writeValueAsString(watchIds);

		Cart cart = new Cart();
		cart.setItems(Arrays.asList(watchIds));

		when(checkoutService.checkout(cart)).thenReturn(360.0);

		this.mockMvc
				.perform(post("/checkout")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(jsonRequest))
				.andDo(print())
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json("{\"price\":360}"));
		
		

	}
	
	/**
	 * Test response for empty input list
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkoutShouldRetunZero() throws Exception {
		String[] watchIds = new String[] { };
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonRequest = objectMapper.writeValueAsString(watchIds);


		this.mockMvc
				.perform(post("/checkout")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(jsonRequest))
				.andDo(print())
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json("{\"price\":0}"));

	}
	
	/**
	 * Test response for single watch checkout
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkoutSingle() throws Exception {
		String[] watchIds = new String[] { "001"};
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonRequest = objectMapper.writeValueAsString(watchIds);
		Cart cart = new Cart();
		cart.setItems(Arrays.asList(watchIds));

		when(checkoutService.checkout(cart)).thenReturn(100.0);

		this.mockMvc
				.perform(post("/checkout")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(jsonRequest))
				.andDo(print())
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json("{\"price\":100}"));

	}
	
	/**
	 * Test response for multiple times an offer
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkoutMultipleOffers() throws Exception {
		String[] watchIds = new String[] { "002","002","002","002"};
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonRequest = objectMapper.writeValueAsString(watchIds);
		Cart cart = new Cart();
		cart.setItems(Arrays.asList(watchIds));

		when(checkoutService.checkout(cart)).thenReturn(240.0);

		this.mockMvc
				.perform(post("/checkout")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8").content(jsonRequest))
				.andDo(print())
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json("{\"price\":240}"));

	}
	
}
