package com.cognizant.truyum.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionHandler {

	public static final String PROPERTY_FILE = "../truYum/src/connection.properties";
	public static final String DRIVER = "driver";
	public static final String URL = "connection-url";
	public static final String USER_NAME = "user";
	public static final String PASSWORD = "password";

	private static Connection connection = null;
	private static Properties props = null;
	private static ConnectionHandler instance = null;

	
	
	private ConnectionHandler() {
		loadProperties();
		try {
			Class.forName(props.getProperty(DRIVER));
			connection = DriverManager.getConnection(props.getProperty(URL), props.getProperty(USER_NAME),
					props.getProperty(PASSWORD));
			
			if(connection != null) {
				System.out.println("Database connection established!!");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public Connection getConnection() {
		return connection;
	}

	public static ConnectionHandler getInstance() {

		instance = new ConnectionHandler();

		return instance;
	}
	

	private void loadProperties() {
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(PROPERTY_FILE);
			props = new Properties();
			props.load(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
