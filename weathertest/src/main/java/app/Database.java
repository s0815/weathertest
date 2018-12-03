package app;

import java.sql.*;


public class Database {

	String dbHostname="debiandev";
	String dbPort="3306";
	String dbName="weather";
	String dbUsername="dbtest";
	String dbPassword="dbtest";

	
	public boolean checkDatabaseClass() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	public boolean connectToDatabase() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mariadb://"
							+dbHostname+":"+dbPort+"/"+dbName, dbUsername, dbPassword);
			// here sonoo is database name, root is username and password
			Statement stmt = con.createStatement();
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		System.out.println("DB Connect OK");
		return true;
	}
}
