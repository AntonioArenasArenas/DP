package controllers.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import controllers.AbstractController;
import domain.Customer;

@Controller
@RequestMapping("/customer/worker")
public class CustomerWorkerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private CustomerService		customerService;


	// Constructors -----------------------------------------------------------

	public CustomerWorkerController() {
		super();
	}
	
	// Showing -----------------------------------------------------------------
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int id) {
		ModelAndView result;
		Customer customer;

		customer = this.customerService.findOne(id);
		Assert.notNull(customer);
		result = this.showModelAndView(customer);

		return result;
	}

	// Ancillary methods ------------------------------------------------------
	
	protected ModelAndView showModelAndView(final Customer customer) {
		ModelAndView result;
		result = new ModelAndView("customer/show");
		result.addObject("customer", customer);

		return result;
	}

}
