package ua.nure.kharkov.practice8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {
	// WARNING!!!
		// you have to change HOST_IP_ADDRESS
		// to MySQL ip address!!!
		private static final String URL = "jdbc:mysql://localhost:3306/testdb?user=test&password=serwak96";

		private static Connection con;
		private static Statement stmt;
		private static ResultSet rs;

		
		public static void main(String[] args) throws SQLException {
			try {
				con = DriverManager.getConnection(URL);
				stmt = con.createStatement();
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				try {
					con.close();
				} catch (SQLException se) {
					}
				try {
					stmt.close();
				} catch (SQLException se) {
					}
			}
		}
}
