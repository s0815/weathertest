package app;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import jsonmap.WeatherJson;


public class Database {

	String dbHostname="debiandev";
	String dbPort="3306";
	String dbName="weather";
	String dbUsername="dbtest";
	String dbPassword="dbtest";
	String dbTable="weathertest";

	Connection dbconnect;
	Statement dbstatement;
	PreparedStatement dbpstatement;
	
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
			dbconnect = DriverManager.getConnection("jdbc:mariadb://"
							+dbHostname+":"+dbPort+"/"+dbName, dbUsername, dbPassword);
			//Statement dbstatement = dbconnect.createStatement();
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		System.out.println("DB Connect OK");
		return true;
	}
	
	public void writeToDatabase(WeatherJson wj) {
		System.out.println(getDate());
		String sqlInsert = "INSERT INTO "+dbTable
					+"(time,temperature,description) "		
					+" VALUES(?,?,?)";
		
		try {
			dbpstatement=dbconnect.prepareStatement(sqlInsert);
			dbpstatement.setString(1, getDate());
			dbpstatement.setInt(2, wj.getMain().getTemp().intValue());
			dbpstatement.setString(3, wj.getWeather().get(0).getDescription());
			
			System.out.println(dbpstatement.toString());
			dbpstatement.executeUpdate();
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void readFromDatabase() {
		ResultSet rs = null;
		try {
			dbstatement = dbconnect.createStatement();
			rs = dbstatement.executeQuery("SELECT * from "+dbTable);
			while(rs.next()) {
				System.out.println(rs.getInt(1) 
						+ " " + rs.getString(2)
						+ " " + rs.getInt(3)
						+ " " + rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public String getDate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(cal.getTime());
		
		
	}
}
