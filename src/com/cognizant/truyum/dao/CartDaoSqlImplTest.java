package com.cognizant.truyum.dao;

import java.util.ArrayList;

import com.cognizant.truyum.model.MenuItem;

public class CartDaoSqlImplTest {

	public static void testAddCartItem() {

		CartDao cartDao = new CartDaoSqlImpl();
		cartDao.addCartItem((long) 1, (long) 1);
		cartDao.addCartItem((long) 1, (long) 2);
		cartDao.addCartItem((long) 1, (long) 3);
		try {
			ArrayList<MenuItem> cartList = (ArrayList<MenuItem>) cartDao.getAllCartItems((long) 1);
			System.out.println("Items added to cart successfully");
			System.out.println(String.format("%-25s%-25s%-25s%-25s%-25s%-20s\n", "Name", "Price", "Active",
					"Date Of Launch", "Category", "Free Delivery"));
			

			for (MenuItem item : cartList) {

				System.out.println(String.format("%-25s%-25s%-25s%-25s%-25s%-20s\n", item.getName(), item.getPrice(),
						item.isActive(), item.getDateOfLaunch(), item.getCategory(), item.isFreeDelivery()));

			}

		} catch (CartEmptyException e) {
			e.printStackTrace();
		}

	}

	public static void testGetAllCartItem() {

		CartDao cartDao = new CartDaoSqlImpl();
		try {

			ArrayList<MenuItem> cartItems = (ArrayList<MenuItem>) cartDao.getAllCartItems((long) 1);
			System.out.println(String.format("%-25s%-25s%-25s%-25s%-25s%-20s\n", "Name", "Price", "Active",
					"Date Of Launch", "Category", "Free Delivery"));

			for (MenuItem item : cartItems) {
				System.out.println(String.format("%-25s%-25s%-25s%-25s%-25s%-20s\n", item.getName(), item.getPrice(),
						item.isActive(), item.getDateOfLaunch(), item.getCategory(), item.isFreeDelivery()));
			}

		} catch (CartEmptyException e) {

			e.printStackTrace();
		}

	}

	public static void testRemoveCartItem() {

		CartDao cartDao = new CartDaoSqlImpl();
		cartDao.removeCartItem((long) 1, (long) 3);
		cartDao.removeCartItem((long) 1, (long) 2);
		try {
			ArrayList<MenuItem> cartLefts = (ArrayList<MenuItem>) cartDao.getAllCartItems((long) 1);
			if (cartLefts.isEmpty()) {
				throw new CartEmptyException("Cart is Empty!!!");
			} else {
				for (MenuItem item : cartLefts) {
					System.out.println(String.format("%-25s%-25s%-25s%-25s%-25s%-20s\n", item.getName(),
							item.getPrice(), item.isActive(), item.getDateOfLaunch(), item.getCategory(),
							item.isFreeDelivery()));
				}
			}
		} catch (CartEmptyException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		testAddCartItem();
		System.out.println("1---------------------------------------------------------------");
		testGetAllCartItem();
		System.out.println("2---------------------------------------------------------------");
		testRemoveCartItem();
		System.out.println("3---------------------------------------------------------------");

	}

}
