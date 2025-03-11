package com.exchange.model;

public class Item {
	private String name;
	private String category;
	private double price;

	public Item() {

	}

	public Item(String name, String category, double price) {
		this.name = name;
		this.category = category;
		this.price = price;
	}

	public Item(String string, String string2, String string3) {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
