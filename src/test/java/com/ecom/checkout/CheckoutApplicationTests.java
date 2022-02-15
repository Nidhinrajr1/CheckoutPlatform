package com.ecom.checkout;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.ecom.checkout.controller.CheckoutController;
import com.ecom.checkout.service.CheckoutService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CheckoutController.class)
class CheckoutApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CheckoutService checkoutService;

	@Test
	public void checkoutShouldGetPriceFromService() throws Exception {
		String[] watchIds = new String[] { "001", "002", "001","001", "004", "003" };
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonRequest = objectMapper.writeValueAsString(watchIds);

		MvcResult result = this.mockMvc
				.perform(post("/checkout")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8")
				.content(jsonRequest)).andDo(print())
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().json("{\"price\":360}"))
				.andReturn();

		String content = result.getResponse().getContentAsString();
		System.out.println("Response: "+content);
	}

}
