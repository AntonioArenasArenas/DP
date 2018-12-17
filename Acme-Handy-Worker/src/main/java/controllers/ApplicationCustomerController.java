package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import domain.Application;

@Controller
@RequestMapping("/application/customer")
public class ApplicationCustomerController extends AbstractController {

	@Autowired
	private ApplicationService applicationService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<Application> applications;

		applications = applicationService.findCustomerApplications();

		result = new ModelAndView("application/list");
		result.addObject("applications", applications);
		result.addObject("requestURI", "application/customer/list.do");

		return result;

	}

}
