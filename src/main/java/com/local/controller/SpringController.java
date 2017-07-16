package com.local.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.local.bisuness.ActorMgr;
import com.local.model.Actor;
import com.local.model.User;

@Controller
public class SpringController {

	@Autowired(required = true)
	private ActorMgr actorMgr;

	// commented

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getIndexPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		// return modelAndView;
		return "index";
	}

	@RequestMapping(value = "/getAllActors", method = RequestMethod.GET)
	public @ResponseBody Object getAllActors() {
		System.out.println("in controller");
		return actorMgr.getAllActors();

	}

	@RequestMapping(value = "/getActorDetails/{actorId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object getActorDetails(@PathVariable("actorId") String actorId) {
		System.out.println("in controller");
		Actor actor = null;
		try {
			actor = actorMgr.getActorById(actorId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return actor;
	}

	@RequestMapping(value = "/getjasperReport/{actorId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object getjasperReport(@PathVariable("actorId") String actorId) {
		System.out.println("in controller");
		Actor actor = null;
		try {
			actor = actorMgr.getjasperReport(actorId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return actor;
	}

	@RequestMapping(value = "/getLoginPage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getLoginPage(HttpServletResponse response) throws IOException {
		System.out.println("In controller");
		return "create_user";
	}

	@RequestMapping(value = "/createUser", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object createUser(HttpServletRequest request, HttpServletResponse response,
			@RequestBody User user) throws IOException {
		// User user = new User();
		String userName = user.getUserName();
		// (String) request.getParameter("userName");
		String password = user.getPassword();
		// (String) request.getParameter("password");
		String emailId = user.getEmail();
		// (String) request.getParameter("emailId");

		user.setUserName(userName);
		user.setEmail(emailId);
		user.setPassword(password);
		String ContentType = "application/json";
		response.setContentType(ContentType);
		return actorMgr.createUser(user);
	}
	
	@RequestMapping(value = "/getJasperReport", method = RequestMethod.GET)
	public @ResponseBody Object getJasperReport(HttpServletResponse response) throws IOException {
		System.out.println("In controller");
		byte[] responseInBytes = actorMgr.getJasperReport();
		String bytesInString = new String(responseInBytes);
		Actor actor = new Actor();
		response.setContentType("application/pdf");
		actor.setFirstName(bytesInString);
		return actor;
		
	}

}
