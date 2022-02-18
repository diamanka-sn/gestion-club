package sn.uasz.tp2.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnexion {
	static Connection connection;

	public static Connection conDb() {
		String server = "localhost";
		String port = "3306";
		String db = "parking";
		String usernName = "root";
		String password = "";
		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + server +"/" + db, usernName,
					password);
			return connection;
		} catch (Exception e) {
			System.err.println("Echecs connexion base de donnees" + e.getMessage());
			return null;
		}

	}

	

}
