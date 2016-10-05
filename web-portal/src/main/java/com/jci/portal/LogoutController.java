package com.jci.portal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	protected String logout(final HttpServletRequest request, HttpServletResponse response) {
		logger.debug("Performing logout");
		invalidateSession(request, response);
		final String logoutPath = "https://login.microsoftonline.com/common/oauth2/logout?post_logout_redirect_uri=http://http://13.68.114.134:8765/web-portal";
		return "redirect:" + logoutPath;
	}

	private void invalidateSession(HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession() != null) {
			response.setHeader("Cache-Control", "no-cache"); // Forces caches to
																// obtain a new
																// copy of the
																// page from the
																// origin server
			response.setHeader("Cache-Control", "no-store"); // Directs caches
																// not to store
																// the page
																// under any
																// circumstance
			response.setDateHeader("Expires", 0); // Causes the proxy cache to
													// see the page as "stale"
			response.setHeader("Pragma", "no-cache"); // HTTP 1.0 backward
			request.getSession().invalidate();
		}
	}

}
