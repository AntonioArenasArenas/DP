/*
 * WorkerController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.worker;

import java.util.Collection;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

import domain.Actor;
import domain.SystemData;
import domain.Worker;

import security.UserAccount;
import security.UserAccountService;
import services.ActorService;
import services.SystemDataService;
import services.WorkerService;

@Controller
@RequestMapping("/worker")
public class WorkerController extends AbstractController {

	
	@Autowired
	private WorkerService workerService;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private SystemDataService systemDataService;

	
	// Constructors -----------------------------------------------------------

	public WorkerController() {
		super();
	}
	
	// Edit
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Worker worker;

		worker = this.workerService.create() ;
		result = this.createEditModelAndView(worker);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
	
		Worker worker = workerService.findByPrincipal();
		Assert.notNull(worker);
		result = this.createEditModelAndView(worker);

		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Worker worker, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()){
			
			result = this.createEditModelAndView(worker);
			System.out.println(binding.getAllErrors());
			
			}
		else
			try {	
				
				UserAccount userAccount = worker.getUserAccount();
				if(worker.getId()==0){
					final String password = new Md5PasswordEncoder().encodePassword(userAccount.getPassword(), null);
					userAccount.setPassword(password);
				}
				userAccount = this.userAccountService.save(userAccount);
				worker.setUserAccount(userAccount);
				
				this.workerService.save(worker);
				result = new ModelAndView("redirect:/");
				
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(worker, "worker.commit.error");
		}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

				protected ModelAndView createEditModelAndView(final Worker worker) {
					ModelAndView result;

					result = this.createEditModelAndView(worker, null);

					return result;
				}

				protected ModelAndView createEditModelAndView(final Worker worker, final String messageError) {
					ModelAndView result;
					SystemData data = (SystemData) systemDataService.findAll().toArray()[0];

					result = new ModelAndView("worker/edit");
					result = new ModelAndView("worker/create");
					result.addObject("worker", worker);
					result.addObject("message", messageError);
					result.addObject("data", data);

					return result;
				}

}
