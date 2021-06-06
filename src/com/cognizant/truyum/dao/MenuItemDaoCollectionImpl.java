package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoCollectionImpl implements MenuItemDao {

	private static List<MenuItem> menuItemList;

	public MenuItemDaoCollectionImpl() {

		if (menuItemList == null) {

			List<MenuItem> menu = new ArrayList<>();

			try {

				MenuItem m0 = new MenuItem(1, "Sandwitch", 99f, true, DateUtil.convertToDate("15/03/2017"),
						"Main Course", true);
				MenuItem m1 = new MenuItem(2, "Burger", 129f, true, DateUtil.convertToDate("23/12/2017"), "Main Course",
						false);
				MenuItem m2 = new MenuItem(3, "Pizza", 149f, true, DateUtil.convertToDate("21/08/2018"), "Main Course",
						false);
				MenuItem m3 = new MenuItem(4, "French Fries", 57f, false, DateUtil.convertToDate("02/07/2017"),
						"Starters", true);
				MenuItem m4 = new MenuItem(5, "Chocolate Brownie", 32f, true, DateUtil.convertToDate("02/11/2022"),
						"Dessert", true);

				menu.add(m0);
				menu.add(m1);
				menu.add(m2);
				menu.add(m3);
				menu.add(m4);
				menuItemList = menu;

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public List<MenuItem> getMenuItemListAdmin() {

		return menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {

		List<MenuItem> customerMenu = new ArrayList<>();

		for (MenuItem item : menuItemList) {

			if (item.isActive() == true && (item.getDateOfLaunch().before(new Date()))) {
				customerMenu.add(item);
			}
		}

		return customerMenu;

	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {

		for (MenuItem m : menuItemList) {
			if (m.getId() == menuItem.getId()) {

				m.setId(menuItem.getId());
				m.setName(menuItem.getName());
				m.setPrice(menuItem.getPrice());
				m.setActive(menuItem.isActive());
				m.setDateOfLaunch(menuItem.getDateOfLaunch());
				m.setCategory(menuItem.getCategory());
				m.setFreeDelivery(menuItem.isFreeDelivery());

			}
		}

	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {

		for (MenuItem m : menuItemList) {
			if (m.getId() == menuItemId) {
				return m;
			}
		}

		return null;
	}

}
