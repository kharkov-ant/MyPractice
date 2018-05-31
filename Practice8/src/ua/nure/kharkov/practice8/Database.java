package ua.nure.kharkov.practice8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private static final String URL = "jdbc:mysql://localhost:3306/testdb?user=test&password=serwak96";
	private static Connection con;
	private static Statement stmt;

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
