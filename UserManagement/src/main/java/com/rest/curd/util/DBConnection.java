/**
 * 
 */
package com.rest.curd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author ARATI-AMOL
 *
 */
public class DBConnection {
	
	public Connection getConnection(){
		Connection con = null;
		 try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.
				        getConnection("jdbc:mysql://localhost:3306/usermanagement"
				            ,"root","root");
			if(con!=null){
				System.out.println("connection sucessful");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

}
