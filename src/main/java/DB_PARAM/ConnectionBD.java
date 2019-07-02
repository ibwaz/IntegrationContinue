package DB_PARAM;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class ConnectionBD {
	// Propriétés

	private static final String url = "jdbc:mysql://localhost/db_metro";
	private static final String user = "root";
	private static final String pass = "";
	private static final String driver = "com.mysql.jdbc.Driver";
	private static String Message;

	// Construct

	// Getteurs
	public static String getUrl() {
		return url;
	}

	public static String getUser() {
		return user;
	}

	public static String getMsg() {
		return Message;
	}

	public static String getPass() {
		return pass;
	}

	public static String getDriver() {
		return driver;
	}

	public static ResultSet executerSelect(String query) {
		Connection cnx = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			cnx = (Connection) DriverManager.getConnection(url, user, pass);
			Statement st = (Statement) cnx.createStatement();
			rs = st.executeQuery(query);
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
		return rs;
	}

	public static boolean executerCrud(String query) {
		Connection cnx = null;
		Statement stmt = null;
		boolean b = true;
		try {
			Class.forName(driver);
			cnx = (Connection) DriverManager.getConnection(url, user, pass);
			stmt = (Statement) cnx.createStatement();
			stmt.executeUpdate(query);
		} catch (Exception e) {
			b = false;

		}
		
		return b;
	}

}
