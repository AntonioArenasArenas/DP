/*
 * WelcomeController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.SystemDataService;
import domain.SystemData;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public WelcomeController() {
		super();
	}
	
	@Autowired
	private SystemDataService systemDataService;

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/index")
	public ModelAndView index(@CookieValue("language") final String lang) {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;
		SystemData data = (SystemData) systemDataService.findAll().toArray()[0];

		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());
		result = new ModelAndView("welcome/index");
		result.addObject("data", data);
		result.addObject("moment", moment);
		result.addObject("language", lang);

		return result;
	}
}
