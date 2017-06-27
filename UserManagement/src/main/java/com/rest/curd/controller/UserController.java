/**
 * 
 */
package com.rest.curd.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.curd.service.UserService;
import com.rest.curd.domain.User;

/**
 * @author ARATI-AMOL
 *
 */

@RestController
public class UserController {
	
	UserService userService = new UserService();
	
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String FAILURE_RESULT="<result>failure</result>";

	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public List<User> getUsers(){
	      return userService.getAllUsers();
	}
	
	
	@RequestMapping(value="/users/{userid}", method=RequestMethod.GET)
	public List<User> getUser(@PathVariable("userid") int userid){
	     return userService.getUser(userid);
	}
	
	
	@RequestMapping(value="/users/createuser", method=RequestMethod.POST)
	public String createUser(@RequestBody User user) throws IOException{
		
			System.out.println(user.getName());
			System.out.println(user.getProfession());
		
		
	      int result = userService.addUser(user);
	      if(result == 1){
	         return SUCCESS_RESULT;
	      }
	      return FAILURE_RESULT;
	  }
	
	@RequestMapping(value="/users/updateuser/{userid}", method=RequestMethod.PUT)
	public String updateUser(@RequestBody User user, 
			@PathVariable("userid") int userid) throws IOException{
			//User user = new User();
		
			user.setId(userid);			
			System.out.println(""+user.getId());
			System.out.println(user.getName());
			System.out.println(user.getProfession());
			
	      int result = userService.updateUser(user);
	      if(result == 1){
	         return SUCCESS_RESULT;
	      }
	      return FAILURE_RESULT;
	  }
	
	
	@RequestMapping(value="/users/deleteuser/{userid}", method=RequestMethod.DELETE)
	public String deleteUser(@RequestBody User user, 
			@PathVariable("userid") int userid){
	      int result = userService.deleteUser(userid);
	      if(result == 1){
	         return SUCCESS_RESULT;
	      }
	      return FAILURE_RESULT;
	}

	@RequestMapping(value="/users", method=RequestMethod.OPTIONS, produces = "application/xml")
	public String getSupportedOperations(){
	      return "<operations>GET, PUT, POST, DELETE</operations>";
	}
}
