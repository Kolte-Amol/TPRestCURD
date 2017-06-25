/**
 * 
 */
package com.rest.curd.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.rest.curd.domain.User;
import com.rest.curd.util.DBConnection;

/**
 * @author ARATI-AMOL
 *
 */
public class UserDao {
	

	   public List<User> getAllUsers(){
		   List<User> userList = new ArrayList<User>();
		   DBConnection dbConnection = new DBConnection();
		   Connection con = dbConnection.getConnection();
		   
		   if(con!=null){
			   Statement stmt;
			try {
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE del ='N'");
				while (rs.next()) {	
					
					User user = new User();
					
					user.setId(rs.getInt("id"));
					user.setName(rs.getString("name"));
					user.setProfession(rs.getString("profession"));
					userList.add(user);					 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   
		   }
	      return userList;
	   }

	   public List<User> getUser(int id){
		   List<User> userList = new ArrayList<User>();
		   DBConnection dbConnection = new DBConnection();
		   Connection con = dbConnection.getConnection();
		   
		   if(con!=null){
			   Statement stmt;
			try {
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE id ="+id);
				while (rs.next()) {	
					
					User user = new User();
					
					user.setId(rs.getInt("id"));
					user.setName(rs.getString("name"));
					user.setProfession(rs.getString("profession"));
					userList.add(user);					 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   
		   }
	      return userList;
	   }

	   public int addUser(User user){
		   DBConnection dbConnection = new DBConnection();
		   Connection con = dbConnection.getConnection();
		   Calendar calendar = Calendar.getInstance();
		   java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
		   
		   if(con!=null){
			   
			try {
				
					String query ="INSERT INTO user (name, profession, createdby, createdts, modifyby, modts, del) VALUES (?,?,?,?,?,?,?)";
				
					PreparedStatement preparedStmt = con.prepareStatement(query);
					preparedStmt.setString(1, user.getName().toString().trim());
					preparedStmt.setString(2, user.getProfession().toString().trim());
					preparedStmt.setString(3, System.getProperty("user.name"));
					preparedStmt.setDate(4, currentDate);
					preparedStmt.setString(5, System.getProperty("user.name"));
					preparedStmt.setDate(6, currentDate);
					preparedStmt.setString(7, "N");
					
					 preparedStmt.execute();				      
				     con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
			   
		   }
	      return 1;
	   }

	   public int updateUser(User user){
		   DBConnection dbConnection = new DBConnection();
		   Connection con = dbConnection.getConnection();
		   Calendar calendar = Calendar.getInstance();
		   java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
		   
		   if(con!=null){
			   
			try {
				
					String query ="update user set name = ?, profession = ?, modifyby = ?, modts = ?, del = ?  where id = ?";
				
					PreparedStatement preparedStmt = con.prepareStatement(query);
					preparedStmt.setString(1, user.getName().toString().trim());
					preparedStmt.setString(2, user.getProfession().toString().trim());
					preparedStmt.setString(3, System.getProperty("user.name"));
					preparedStmt.setDate(4, currentDate);					
					preparedStmt.setString(5, "N");
					preparedStmt.setInt(6, user.getId());
					
					 preparedStmt.execute();				      
				     con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
			   
		   }
	      return 1;
	   }

	   public int deleteUser(int id){

		   DBConnection dbConnection = new DBConnection();
		   Connection con = dbConnection.getConnection();
		   Calendar calendar = Calendar.getInstance();
		   java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
		   
		   if(con!=null){
			   
			try {
				
					String query ="update user set modifyby = ?, modts = ?, del = ?  where id = ?";
				
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					preparedStmt.setString(1, System.getProperty("user.name"));
					preparedStmt.setDate(2, currentDate);					
					preparedStmt.setString(3, "Y");
					preparedStmt.setInt(4, id);
					
					 preparedStmt.execute();				      
				     con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
			   
		   }
	      return 1;
	   }

	   private void saveUserList(List<User> userList){
	      try {
	         File file = new File("Users.dat");
	         FileOutputStream fos;

	         fos = new FileOutputStream(file);

	         ObjectOutputStream oos = new ObjectOutputStream(fos);		
	         oos.writeObject(userList);
	         oos.close();
	      } catch (FileNotFoundException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   }

}
