package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

public class CartDaoCollectionImpl implements CartDao {

	private static HashMap<Long, Cart> userCarts;
	ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();

	public CartDaoCollectionImpl() {

		if (userCarts == null) {
			userCarts = new HashMap<Long, Cart>();
		}

	}

	@Override
	public void addCartItem(long userId, long menuItemId) {

		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		MenuItem menuItem = menuItemDao.getMenuItem(menuItemId);

		if (userCarts.containsKey(userId)) {
			menuItemList = (ArrayList<MenuItem>) userCarts.get(userId).getMenuItemList();
			menuItemList.add(menuItem);
		} else {
			ArrayList<MenuItem> menuItemList = new ArrayList<>();
			menuItemList.add(menuItem);
			Cart cart = new Cart(menuItemList, 0);
			cart.setMenuItemList(menuItemList);
			userCarts.put(userId, cart);
		}
	}

	@Override
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {

		List<MenuItem> menuItemList = new ArrayList<MenuItem>();

		try {
			menuItemList = userCarts.get(userId).getMenuItemList();

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		if (menuItemList.isEmpty()) {
			throw new CartEmptyException("Cart is empty");
		}

		float sum = 0.0f;

		for (MenuItem menuItem : menuItemList) {
			sum += menuItem.getPrice();
		}
		userCarts.get(userId).setTotal(sum);

		return menuItemList;
	}

	@Override
	public void removeCartItem(long userId, long menuItemId) {

		ArrayList<MenuItem> menuItemList = (ArrayList<MenuItem>) userCarts.get(userId).getMenuItemList();
		double total = userCarts.get(userId).getTotal();

		for (MenuItem menuItem : menuItemList) {
			if (menuItem.getId() == menuItemId) {
				menuItemList.remove(menuItemList.indexOf(menuItem));
				total = total - menuItem.getPrice();
				break;
			}
			userCarts.get(userId).setMenuItemList(menuItemList);
			userCarts.get(userId).setTotal(total);
		}

	}

}
