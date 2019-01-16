/*
 * CustomerController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.customer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccount;
import security.UserAccountService;
import services.ActorService;
import services.CustomerService;
import controllers.AbstractController;
import domain.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {

	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private ActorService actorService;

	
	// Constructors -----------------------------------------------------------

	public CustomerController() {
		super();
	}
	
	// Edit
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Customer customer;

		customer = this.customerService.create() ;
		result = this.createEditModelAndView(customer);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
	
		Customer customer = customerService.findByPrincipal();
		
		Assert.notNull(customer);
		result = this.createEditModelAndView(customer);

		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Customer customer, final BindingResult binding) {
		ModelAndView result;
		System.out.println(binding.getFieldErrors());
		if (binding.hasErrors()){
			
			result = this.createEditModelAndView(customer);
			System.out.println(binding.getAllErrors());
			
			}
		else
			try {
				
				UserAccount userAccount = customer.getUserAccount();
				if(customer.getId()==0){
					final String password = new Md5PasswordEncoder().encodePassword(userAccount.getPassword(), null);
					userAccount.setPassword(password);
				}
				
				userAccount = this.userAccountService.save(userAccount);
				customer.setUserAccount(userAccount);
				
				this.customerService.save(customer);
				result = new ModelAndView("redirect:/");
				
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(customer, "customer.commit.error");
		}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

				protected ModelAndView createEditModelAndView(final Customer customer) {
					ModelAndView result;

					result = this.createEditModelAndView(customer, null);

					return result;
				}

				protected ModelAndView createEditModelAndView(final Customer customer, final String messageError) {
					ModelAndView result;

					result = new ModelAndView("customer/edit");
					result = new ModelAndView("customer/create");
					result.addObject("customer", customer);
					result.addObject("message", messageError);

					return result;
				}

}
