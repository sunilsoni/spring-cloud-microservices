package com.jci.portal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("Performing logout");
		invalidateSession(request);
		return "redirect:index.html";
	}

	public void invalidateSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
	}

}
