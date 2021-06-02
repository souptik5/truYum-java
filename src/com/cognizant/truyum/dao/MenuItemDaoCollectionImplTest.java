package com.cognizant.truyum.dao;

import java.util.List;

import com.cognizant.truyum.model.MenuItem;


public class MenuItemDaoCollectionImplTest {

	public static void testGetMenuItemListAdmin() {
		
		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		List<MenuItem> menuItem = menuItemDao.getMenuItemListAdmin();
		menuItem.stream().forEach(System.out::println);

	}

	public static void testGetMenuItemListCustomer() {

	}

	public static void testModifyMenuItem() {

	}

	public static void testGetMenuItem() {

	}

	public static void main(String[] args) {
		
		MenuItemDaoCollectionImplTest.testGetMenuItemListAdmin();
		
	}

}
