package com.ecom.checkout.model;

import java.io.Serializable;

public class Watch implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Unique watch id */
	private String id;

	/* Watch name */
	private String name;

	/* Watch price */
	private Double price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
