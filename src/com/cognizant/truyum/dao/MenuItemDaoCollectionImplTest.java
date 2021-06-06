package com.cognizant.truyum.dao;

import java.text.ParseException;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoCollectionImplTest {

	public static void testGetMenuItemListAdmin() {

		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		List<MenuItem> menuItem = menuItemDao.getMenuItemListAdmin();
		menuItem.stream().forEach(System.out::println);

	}

	public static void testGetMenuItemListCustomer() {

		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		List<MenuItem> menuItem = menuItemDao.getMenuItemListCustomer();
		menuItem.stream().forEach(System.out::println);

	}

	public static void testModifyMenuItem() throws ParseException {

		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		MenuItem menuItem = new MenuItem(1, "Sandwitch", 50f, true, DateUtil.convertToDate("15/03/2017"), "Snacks",
				true);
		menuItemDao.modifyMenuItem(menuItem);
		menuItemDao.getMenuItem(menuItem.getId());
		System.out.println("Item Modified!");

	}


	public static void main(String[] args) {

		MenuItemDaoCollectionImplTest.testGetMenuItemListAdmin();
		System.out.println(
				"========================================================================================================");

		MenuItemDaoCollectionImplTest.testGetMenuItemListCustomer();
		System.out.println(
				"========================================================================================================");

		try {
			MenuItemDaoCollectionImplTest.testModifyMenuItem();
			System.out.println("Customer's List");
			MenuItemDaoCollectionImplTest.testGetMenuItemListCustomer();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(
				"========================================================================================================");

	}

}
