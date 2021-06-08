package com.cognizant.truyum.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

import com.cognizant.truyum.model.MenuItem;

public class MenuItemDaoSqlImp implements MenuItemDao {

	@Override
	public List<MenuItem> getMenuItemListAdmin() {

		Connection connection = ConnectionHandler.getInstance().getConnection();
		PreparedStatement ps = null;
		final String QUERY = "select * from menu_item";

		ArrayList<MenuItem> menuItemList = new ArrayList<>();

		try {
			ps = connection.prepareStatement(QUERY);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("menu_id");
				String name = rs.getString("name");
				float price = rs.getFloat("price");
				boolean active = rs.getBoolean("active");
				Date date1 = rs.getDate("date_of_launch");
				String category = rs.getString("category");
				boolean free_delivery = rs.getBoolean("free_delivery");

				MenuItem menuItem = new MenuItem(id, name, price, active, date1, category, free_delivery);
				menuItemList.add(menuItem);
			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return menuItemList;
	}

	@Override
	public List<MenuItem> getMenuItemListCustomer() {

		Connection connection = ConnectionHandler.getInstance().getConnection();
		PreparedStatement ps = null;
		final String QUERY = "select * from menu_item where date_of_launch < curdate() and active = 1";

		ArrayList<MenuItem> menuItemList = new ArrayList<>();

		try {

			ps = connection.prepareStatement(QUERY);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("menu_id");
				String name = rs.getString("name");
				float price = rs.getFloat("price");
				boolean active = rs.getBoolean("active");
				Date date1 = rs.getDate("date_of_launch");
				String category = rs.getString("category");
				boolean free_delivery = rs.getBoolean("free_delivery");

				MenuItem menuItem = new MenuItem(id, name, price, active, date1, category, free_delivery);
				menuItemList.add(menuItem);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return menuItemList;
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {

		Connection connection = ConnectionHandler.getInstance().getConnection();
		PreparedStatement ps = null;
		final String QUERY = "select * from menu_item where menu_id = ?";
		MenuItem menuItem = null;

		try {
			ps = connection.prepareStatement(QUERY);
			ps.setLong(1, menuItemId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("menu_id");
				String name = rs.getString("name");
				float price = rs.getFloat("price");
				boolean active = rs.getBoolean("active");
				Date date1 = rs.getDate("date_of_launch");
				String category = rs.getString("category");
				boolean free_delivery = rs.getBoolean("free_delivery");

				menuItem = new MenuItem(id, name, price, active, date1, category, free_delivery);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return menuItem;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {

		Connection connection = ConnectionHandler.getInstance().getConnection();
		PreparedStatement ps = null;

		int id = menuItem.getId();
		String name = menuItem.getName();
		float price = menuItem.getPrice();
		boolean active = menuItem.isActive();
		java.sql.Date date = (java.sql.Date) new Date(menuItem.getDateOfLaunch().getTime());
		String category = menuItem.getCategory();
		boolean free_delivery = menuItem.isFreeDelivery();
		int act = 0;
		if (active == true) {
			act = 1;
		} else {
			act = 0;
		}
		int fd = 0;
		if (free_delivery == true) {
			fd = 1;
		} else {
			fd = 0;
		}

		final String QUERY = "update menu_item set name = ?, price = ?, active = ?, date_of_launch = ?, category = ?, free_delivery = ? where menu_id = ?";

		try {
			ps = connection.prepareStatement(QUERY);
			ps.setString(1, name);
			ps.setFloat(2, price);
			ps.setInt(3, act);
			ps.setDate(4, date);
			ps.setString(5, category);
			ps.setInt(6, fd);
			ps.setInt(7, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
