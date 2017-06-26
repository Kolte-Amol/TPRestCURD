/**
 * 
 */
package com.rest.curd.service;

import java.util.List;

import com.rest.curd.dao.UserDao;
import com.rest.curd.domain.User;

/**
 * @author ARATI-AMOL
 *
 */
public class UserService {

	UserDao userDao = new UserDao();
	
//	UserDATDao userDao = new UserDATDao();
	
	
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	public List<User> getUser(int userid) {
		return userDao.getUser(userid);
	}

	public int addUser(User user) {
		return userDao.addUser(user);
	}

	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

	public int deleteUser(int userid) {
		
		return userDao.deleteUser(userid);
	}

}
