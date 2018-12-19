/*
 * ProfileController.java
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

import domain.Customer;
import domain.Worker;

import services.CustomerService;
import services.WorkerService;

@Controller
@RequestMapping("/profile")
public class ProfileController extends AbstractController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private WorkerService workerService;
	// Action-1 ---------------------------------------------------------------		

	@RequestMapping(value = "createCustomer", method = RequestMethod.GET)
	public ModelAndView createCustomer() {
		ModelAndView result;
		Customer customer = this.customerService.create();

		result = this.createEditModelAndView(customer);

		return result;
	}

	// Action-2 ---------------------------------------------------------------		

	@RequestMapping(value = "createWorker", method = RequestMethod.GET)
	public ModelAndView createWorker() {
		ModelAndView result;

		Worker worker = this.workerService.create();

		result = this.createEditModelAndView(worker);

		return result;
	}

	// Action-2 ---------------------------------------------------------------		

	@RequestMapping("/action-3")
	public ModelAndView action3() {
		throw new RuntimeException("Oops! An *expected* exception was thrown. This is normal behaviour.");
	}
	
	// Ancillary methods ------------------------------------------------------

		protected ModelAndView createEditModelAndView(final Customer customer) {
			ModelAndView result;

			result = this.createEditModelAndView(customer, null);

			return result;
		}

		protected ModelAndView createEditModelAndView(final Customer customer, final String message) {
			ModelAndView result;
			


			result = new ModelAndView("customer/edit");
			result.addObject("customer", customer);

			return result;
		}
		
		protected ModelAndView createEditModelAndView(final Worker worker) {
			ModelAndView result;

			result = this.createEditModelAndView(worker, null);

			return result;
		}

		protected ModelAndView createEditModelAndView(final Worker worker, final String message) {
			ModelAndView result;
			


			result = new ModelAndView("worker/edit");
			result.addObject("worker", worker);

			return result;
		}


}
