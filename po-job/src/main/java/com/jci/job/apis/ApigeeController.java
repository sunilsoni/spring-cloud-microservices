package com.jci.job.apis;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



/**
 * REST endpoint for the user functionality
 * 
 * @author jci
 *
 */
@RestController
@RequestMapping("/")
public class ApigeeController {

	@Value("${mail.domain}")
	private String mailDomain;

	
	/**
	 * Return all users
	 * 
	 * @return
	 */
	/*@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
	public List<UserDTO> getUsers() {
		return users;
	}*/

	/**
	 * Return user associated with specific user name
	 * 
	 * @param userName
	 * @return
	 */
	/*@RequestMapping(value = "{userName}", method = RequestMethod.GET, headers = "Accept=application/json")
	public UserDTO getUserByUserName(@PathVariable("userName") String userName) {
		UserDTO userDtoToReturn = null;
		for (UserDTO currentUser : users) {
			if (currentUser.getUserName().equalsIgnoreCase(userName)) {
				userDtoToReturn = currentUser;
				break;
			}
		}

		return userDtoToReturn;
	}
	*/
}
