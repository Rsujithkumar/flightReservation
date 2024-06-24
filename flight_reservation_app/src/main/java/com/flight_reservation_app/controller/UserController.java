package com.flight_reservation_app.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flight_reservation_app.repository.UserRepository;
import com.flight_reservation_app.entity.User;

@Controller
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserRepository userRepo;

	@RequestMapping(path = "/showReg")
	public String showReg() {
		LOGGER.info("Inside showreg()");
		return "login/showreg";
	}

	@RequestMapping(path = "/saveReg")
	public String saveReg(@ModelAttribute("user") User user, ModelMap modelMap) {
		LOGGER.info("Inside saveReg()");
		userRepo.save(user);
		modelMap.addAttribute("msg", "Record Saved");
		return "login/showReg";
	}
	@RequestMapping("/showLogin")
	public String showLogin() {
		LOGGER.info("Inside showLogin()");
		return "login/login";
	}

	@RequestMapping("/verifyLogin")
	public String verifyLogin(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelMap modelMap) {
		LOGGER.info("Inside verifyLogin()");
		User user = userRepo.findByEmail(email);
		if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
			return "searchFlights";
		} else {
			modelMap.addAttribute("error", "Invalid username/password");
			return "login/login";
		}

	}
}
