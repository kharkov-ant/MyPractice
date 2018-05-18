package ua.nure.kharkov.practice8.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.kharkov.practice8.db.entity.Group;
import ua.nure.kharkov.practice8.db.entity.User;

public class DBManager {
	private static DBManager instance;

	public static synchronized DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private DBManager() {
	}

	private static final String URL = "jdbc:mysql://localhost:3306/p8db?user=test&password=serwak96";
	private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";
	private static final String SQL_FIND_ALL_GROUPS = "SELECT * FROM groups";
	private static final String SQL_INSERT_USER = "INSERT INTO users VALUES (DEFAULT, ?)";
	private static final String SQL_INSERT_GROUP = "INSERT INTO groups VALUES (DEFAULT, ?)";
	private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
	private static final String SQL_FIND_GROUP_BY_NAME = "SELECT * FROM groups WHERE name=?";
	private static final String SQL_INSERT_USER_IN_GROUP = "INSERT INTO users_groups VALUES (?, ?)";
	private static final String SQL_FIND_USER_GROUP = "SELECT * FROM users_groups WHERE user_id=?";

	public List<User> findAllUsers() throws DBException {
		List<User> users = new ArrayList<>();

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_USERS);

			while (rs.next()) {
				users.add(extractUser(rs));
			}
		} catch (SQLException ex) {
			throw new DBException("Cannot find all users", ex);
		} finally {
			close(rs);
			close(stmt);
			close(con);
		}

		return users;
	}

	public List<Group> findAllGroups() throws DBException {
		List<Group> groups = new ArrayList<>();

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_GROUPS);

			while (rs.next()) {
				groups.add(extractGroup(rs));
			}
		} catch (SQLException ex) {
			throw new DBException("Cannot find all groups", ex);
		} finally {
			close(rs);
			close(stmt);
			close(con);
		}

		return groups;
	}

	public boolean insertUser(User user) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = getConnection();
		try {
			pstmt = con.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getLogin());
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					user.setId(id);
					return true;
				}
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return false;
	}

	public boolean insertGroup(Group group) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = getConnection();
		try {
			pstmt = con.prepareStatement(SQL_INSERT_GROUP, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, group.getName());
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					group.setId(id);
					return true;
				}
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return false;
	}

	public User getUser(String login) throws SQLException {
		User user = null;
		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
		int k = 1;
		pstmt.setString(k++, login);

		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			user = extractUser(rs);
		}
		return user;
	}

	public Group getGroup(String name) throws SQLException {
		Group group = null;
		Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(SQL_FIND_GROUP_BY_NAME);
		int k = 1;
		pstmt.setString(k++, name);

		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			group = extractGroup(rs);
		}
		return group;
	}

//	public List<String> getUserGroups(User user) throws DBException {
//		List<String> list = new ArrayList<>();
//
//		Connection con = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//
//		try {
//			con = getConnection();
//			stmt = con.createStatement();
//			rs = stmt.executeQuery(SQL_FIND_USER_GROUP+user.getId());
//
//			while (rs.next()) {
//				list.add(extractUser(rs));
//			}
//		} catch (SQLException ex) {
//			throw new DBException("Cannot find all users", ex);
//		} finally {
//			close(rs);
//			close(stmt);
//			close(con);
//		}
//
//		return users;
//	}
	
	public void setGroupsForUser(User user, Group group) throws SQLException {
		insertUserIntoGroup(user, group);
	}

	public void setGroupsForUser(User user, Group group1, Group group2) throws SQLException {
		insertUserIntoGroup(user, group1);
		insertUserIntoGroup(user, group2);
	}

	public void setGroupsForUser(User user, Group group1, Group group2, Group group3) throws SQLException {
		insertUserIntoGroup(user, group1);
		insertUserIntoGroup(user, group2);
		insertUserIntoGroup(user, group3);
	}

	private boolean insertUserIntoGroup(User user, Group group) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			pstmt = con.prepareStatement(SQL_INSERT_USER_IN_GROUP);
			int k = 1;
			pstmt.setInt(k++, user.getId());
			pstmt.setInt(k++, group.getId());
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			// (1) write to log

			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return false;
	}

	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setLogin(rs.getString("login"));
		return user;
	}

	private Group extractGroup(ResultSet rs) throws SQLException {
		Group group = new Group();
		group.setId(rs.getInt("id"));
		group.setName(rs.getString("name"));
		return group;
	}

	private Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection(URL);
		// adjust your connection!
		return con;
	}

	private void close(ResultSet rs) throws DBException {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				throw new DBException("Cannot close a resource", ex);
			}
		}
	}

	private void close(Statement stmt) throws DBException {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				throw new DBException("Cannot close a resource", ex);
			}
		}
	}

	private void close(Connection con) throws DBException {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
				throw new DBException("Cannot close a resource", ex);
			}
		}
	}
}
