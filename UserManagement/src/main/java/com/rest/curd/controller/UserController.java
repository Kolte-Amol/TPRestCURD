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
@RequestMapping("/UserService")
public class UserController {
	
	UserService userService = new UserService();
	
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String FAILURE_RESULT="<result>failure</result>";

	
	@RequestMapping(value="/users", method=RequestMethod.GET, produces = "application/xml")
	public List<User> getUsers(){
	      return userService.getAllUsers();
	}
	
	
	@RequestMapping(value="/users/{userid}", method=RequestMethod.GET, produces = "application/xml")
	public User getUser(@PathParam("userid") int userid){
	     return userService.getUser(userid);
	}
	
	
	@RequestMapping(value="/users", method=RequestMethod.POST, produces = "application/xml", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String createUser(@RequestBody MultiValueMap<String, String> formParams, 
			HttpServletResponse servletResponse) throws IOException{
		
		User user = new User();
		 Iterator it = formParams.entrySet().iterator();
		 while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        
		        System.out.println(pair.getKey() + " = " + pair.getValue());
		       
		        if(pair.getKey().toString().trim().equalsIgnoreCase("id")){
		        	user.setId(Integer.parseInt(pair.getValue().toString().trim()));
		        }else if(pair.getKey().toString().trim().equalsIgnoreCase("name")){
		        	user.setName(pair.getValue().toString().trim());
		        }else{
		        	user.setProfession(pair.getValue().toString().trim());
		        }
		        
		        it.remove(); // avoids a ConcurrentModificationException
		    }
		
	      int result = userService.addUser(user);
	      if(result == 1){
	         return SUCCESS_RESULT;
	      }
	      return FAILURE_RESULT;
	  }
	
	@RequestMapping(value="/users", method=RequestMethod.PUT, produces = "application/xml", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String updateUser(@RequestBody MultiValueMap<String, String> formParams, 
			HttpServletResponse servletResponse) throws IOException{
		
		User user = new User();
		 Iterator it = formParams.entrySet().iterator();
		 while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        
		        System.out.println(pair.getKey() + " = " + pair.getValue());
		       
		        if(pair.getKey().toString().trim().equalsIgnoreCase("id")){
		        	user.setId(Integer.parseInt(pair.getValue().toString().trim()));
		        }else if(pair.getKey().toString().trim().equalsIgnoreCase("name")){
		        	user.setName(pair.getValue().toString().trim());
		        }else{
		        	user.setProfession(pair.getValue().toString().trim());
		        }
		        
		        it.remove(); // avoids a ConcurrentModificationException
		    }
		
	      int result = userService.updateUser(user);
	      if(result == 1){
	         return SUCCESS_RESULT;
	      }
	      return FAILURE_RESULT;
	  }
	
	
	@RequestMapping(value="/users/{userid}", method=RequestMethod.DELETE, produces = "application/xml")
	public String deleteUser(@PathParam("userid") int userid){
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
