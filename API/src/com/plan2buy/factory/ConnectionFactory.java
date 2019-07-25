package com.plan2buy.factory;

import java.sql.DriverManager;
import java.sql.*;
//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.PreparedStatement;
//import com.mysql.jdbc.ResultSetRow;;

public class ConnectionFactory {

	private static final String DRIVE = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/plan2buy";
	private static final String USER = "root";
	private static final String PASSW = "admin";

	public Connection createConnection() {

		Connection con = null;

		try {

			Class.forName(DRIVE);
			con =  DriverManager.getConnection(URL, USER, PASSW);

		} catch (Exception e) {
			System.out.println("Error to create a connection" + URL);
			e.printStackTrace();
		}

		return con;
	}

	public void closeConnection(Connection con, PreparedStatement pStan, ResultSet rs) {

		try {

			if (con != null) {

				con.close();
			}

			if (pStan != null) {

				pStan.close();
			}

			if (rs != null) {

				rs.close();
			}

		} catch (Exception e) {

			System.out.println("Erro to close a connection" + URL);
			e.printStackTrace();
		}

	}

}
