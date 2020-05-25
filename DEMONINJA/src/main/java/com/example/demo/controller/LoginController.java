package com.example.demo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constants.ViewConstant;
import com.example.demo.converter.ContactConverter;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.impl.UserService;


@RestController
public class LoginController {
	
	private static final Log logger = LogFactory.getLog(LoginController.class);
	
	@Autowired
	ContactRepository contactService;
	
	@Autowired
	ContactConverter contactConverter;
	
	@Autowired
	UserService userRepository;
	
	@GetMapping("/login")
	public String showLoginForm(Model model,  @RequestParam(name = "error", required = false) String error, @RequestParam(name="logout", required = false) String logout) {
		
		logger.info("/login values error: " + error + " logout: " + logout);
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return ViewConstant.LOGIN;
	}
	
	
	@GetMapping({"/loginsuccess", "/"})
	public String loginCheck() {
		logger.info("Metodo loginCheck");
		return "redirect:/contact/showcontacts";
	}

}
