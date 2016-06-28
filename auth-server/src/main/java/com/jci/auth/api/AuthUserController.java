package com.jci.auth.api;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * REST endpoint to be used by other micro-services using SSO to validate the
 * authentication of the logged in user.
 * 
 * @author jci
 *
 */
@RestController
@RequestMapping("/")
@SessionAttributes("authorizationRequest")
public class AuthUserController {
	
	/**
	 * Return the principal identifying the logged in user
	 * @param user
	 * @return
	 */
	@RequestMapping("/me")
	public Principal getCurrentLoggedInUser(Principal user) {
		System.out.println("### Starting Ending AuthUserController.getCurrentLoggedInUser ####"+user);
		return user;
	}
}
