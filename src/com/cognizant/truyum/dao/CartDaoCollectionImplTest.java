package com.cognizant.truyum.dao;

import java.util.ArrayList;

import com.cognizant.truyum.model.MenuItem;

public class CartDaoCollectionImplTest {

	static CartDao cartDao;

	public static void testAddCartItem() throws CartEmptyException {

		cartDao = new CartDaoCollectionImpl();
		cartDao.addCartItem(1, 3);
		cartDao.addCartItem(1, 1);
		ArrayList<MenuItem> menuItemList = (ArrayList<MenuItem>) cartDao.getAllCartItems(1);

		for (MenuItem menuItem : menuItemList) {
			System.out.println(menuItem);
		}

	}

	public static void testGetAllCartItems() throws CartEmptyException {

		cartDao = new CartDaoCollectionImpl();
		ArrayList<MenuItem> menuItemList = (ArrayList<MenuItem>) cartDao.getAllCartItems(1);

		for (MenuItem menuItem : menuItemList) {
			System.out.println(menuItem);
		}

	}

	public static void testRemoveCartItem() throws CartEmptyException {

		cartDao = new CartDaoCollectionImpl();
		cartDao.removeCartItem(1, 1);
		ArrayList<MenuItem> menuItemList = (ArrayList<MenuItem>) cartDao.getAllCartItems(1);
		for (MenuItem x : menuItemList) {
			System.out.println(x);
		}

	}

	public static void main(String[] args) throws CartEmptyException {

		try {
			testAddCartItem();
			System.out.println("-------------------------------------------------");
			testGetAllCartItems();
			System.out.println("-------------------------------------------------");
			testRemoveCartItem();
		} catch (CartEmptyException e) {
			System.out.println(e);
		}

	}

}
