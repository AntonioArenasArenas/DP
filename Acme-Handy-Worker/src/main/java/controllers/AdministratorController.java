/*
 * AdministratorController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.TaskService;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private TaskService taskService;

	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}

	// Action-1 ---------------------------------------------------------------

	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	public ModelAndView statistics() {
		ModelAndView result;
		
//		Double[] spuser = taskService.tasksPerUserStatistics();

		Double[] sptask = applicationService.getAdminStatisticsPerTask();

		Double[] spoffered = applicationService
				.getAdminStatisticsPriceOffered();

		Double cantChange = applicationService.getAppliCantChange();

		result = new ModelAndView("administrator/statistics");
		
//		result.addObject("maximumpu", spuser[2]);
//		result.addObject("minimumpu", spuser[1]);
//		result.addObject("averagepu", spuser[0]);
//		result.addObject("stdevpu", spuser[3]);
		
		result.addObject("maximumpt", sptask[2]);
		result.addObject("minimumpt", sptask[1]);
		result.addObject("averagept", sptask[0]);
		result.addObject("stdevpt", sptask[3]);

		result.addObject("maximumpo", spoffered[2]);
		result.addObject("minimumpo", spoffered[1]);
		result.addObject("averagepo", spoffered[0]);
		result.addObject("stdevpo", spoffered[3]);

		result.addObject("ratio", cantChange);

		return result;
	}

}
