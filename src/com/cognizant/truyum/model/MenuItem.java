package com.cognizant.truyum.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MenuItem {

	private int id;
	private String name;
	private float price;
	private boolean active;
	private Date dateOfLaunch;
	private String category;
	private boolean freeDelivery;

	/**
	 * @param id
	 * @param name
	 * @param price
	 * @param active
	 * @param dateOfLaunch
	 * @param category
	 * @param freeDelivery
	 */
	public MenuItem(int id, String name, float price, boolean active, Date dateOfLaunch, String category,
			boolean freeDelivery) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.active = active;
		this.dateOfLaunch = dateOfLaunch;
		this.category = category;
		this.freeDelivery = freeDelivery;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getDateOfLaunch() {
		return dateOfLaunch;
	}

	public void setDateOfLaunch(Date dateOfLaunch) {
		this.dateOfLaunch = dateOfLaunch;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isFreeDelivery() {
		return freeDelivery;
	}

	public void setFreeDelivery(boolean freeDelivery) {
		this.freeDelivery = freeDelivery;
	}

	@Override
	public String toString() {
		SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
		return String.format("%-25s$%-25s%-25s%-25s%-25s%-20s\n", getName(), getPrice(), isActive(),
				simple.format(getDateOfLaunch()), getCategory(), isFreeDelivery());
	}

	boolean equals(MenuItem m) {
		if (m.id == this.id) {
			return true;
		} else {
			return false;
		}
	}

}
