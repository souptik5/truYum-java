package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

public class CartDaoSqlImpl implements CartDao {

	@Override
	public void addCartItem(long userId, long menuItemId) {

		Connection connection = ConnectionHandler.getInstance().getConnection();
		PreparedStatement ps = null;

		final String QUERY = "insert into cart (user_id, menu_id) values (?,?)";
		try {

			ps = connection.prepareStatement(QUERY);
			ps.setLong(1, userId);
			ps.setLong(2, menuItemId);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {

		Connection connection = ConnectionHandler.getInstance().getConnection();
//		Connection connection2 = ConnectionHandler.getInstance().getConnection();
		PreparedStatement ps = null;
//		PreparedStatement ps2 = null;

		ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
		Cart cart = new Cart(menuItemList, 0);

		final String QUERY = "select c.menu_id, \r\n" + "(select m.name from menu_item m \r\n"
				+ "where c.menu_id = m.menu_id) as `Item Name`,\r\n" + "(select m.price from menu_item m \r\n"
				+ "where c.menu_id = m.menu_id) as `Price`,\r\n" + "(select m.active from menu_item m \r\n"
				+ "where c.menu_id = m.menu_id) as `Active`,\r\n" + "(select m.date_of_launch from menu_item m \r\n"
				+ "where c.menu_id = m.menu_id) as `Date of launch`,\r\n" + "(select m.category from menu_item m \r\n"
				+ "where c.menu_id = m.menu_id) as `Category`,\r\n" + "(select m.free_delivery from menu_item m \r\n"
				+ "where c.menu_id = m.menu_id) as `Free Delivery` \r\n" + "from cart c\r\n" + "where c.user_id = ?";

//		final String QUERY2 = "select sum((select m.price from menu_item m \r\n"
//				+ "where c.menu_id = m.menu_id)) as `Total Price` \r\n" + "from cart c \r\n" + "where c.user_id = ?";

		try {

			ps = connection.prepareStatement(QUERY);
			ps.setLong(1, userId);
//			ps2 = connection2.prepareStatement(QUERY2);
//			ps2.setLong(1, userId);
			ResultSet rs = ps.executeQuery();
//			ResultSet rs2 = ps2.executeQuery();

			float total = 0.00f;

			while (rs.next()) {
				int id = rs.getInt("menu_id");
				String item_name = rs.getString("Item Name");
				float price = rs.getFloat("Price");
				total +=price;
				boolean active = rs.getBoolean("Active");
				Date date = rs.getDate("Date of launch");
				String category = rs.getString("Category");
				boolean free_delivery = rs.getBoolean("Free Delivery");

				MenuItem m1 = new MenuItem(id, item_name, price, active, date, category, free_delivery);
				menuItemList.add(m1);
			}

//			while (rs2.next()) {
//				total = rs.getFloat("Total Price");
//			}

			cart.setTotal(total);
			cart.setMenuItemList(menuItemList);
			
			System.out.println(cart.getTotal());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return menuItemList;
	}
	

	@Override
	public void removeCartItem(long userId, long menuItemId) {

		Connection connection = ConnectionHandler.getInstance().getConnection();
		PreparedStatement ps = null;

		final String QUERY = "delete from cart where user_id = ? and menu_id = ?";

		try {
			ps = connection.prepareStatement(QUERY);
			ps.setLong(1, userId);
			ps.setLong(2, menuItemId);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
