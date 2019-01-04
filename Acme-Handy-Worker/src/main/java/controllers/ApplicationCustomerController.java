package controllers;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.CreditCardService;
import domain.Application;

@Controller
@RequestMapping("/application/customer")
public class ApplicationCustomerController extends AbstractController {

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private CreditCardService creditCardService;

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

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int applicationId) {
		ModelAndView result;
		Application application;

		application = applicationService.findOne(applicationId);
		Assert.notNull(application);

		result = createEditModelAndView(application);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Application application,
			BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(application);
		} else {
			try {
				if (application.getStatus().equals("ACCEPTED")) {
					creditCardService.save(application.getCreditCard());
				}
				Application actual = applicationService.findOne(application
						.getId());
				if (!actual.getComments().equals(null)) {
					applicationService.updateStatus(application,
							actual.getComments());
				} else {
					applicationService.updateStatus(application, "");
				}

				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(application,
						"application.commit.error");
			}
		}

		return result;

	}

	protected ModelAndView createEditModelAndView(Application application) {
		ModelAndView result;
		result = createEditModelAndView(application, null);

		return result;
	}

	private ModelAndView createEditModelAndView(Application application,
			String messageCode) {
		ModelAndView result;
		Collection<String> estados = new LinkedList<String>();
		estados.add("PENDING");
		estados.add("ACCEPTED");
		estados.add("REJECTED");
		Collection<String> comentarios = new LinkedList<String>();
		if (application.getComments() != null) {
			String[] spliteado = application.getComments().split(";");
			comentarios = Arrays.asList(spliteado);
		}
		Collection<String> brandnames = new LinkedList<String>();
		brandnames.add("VISA");
		brandnames.add("MASTERS");
		brandnames.add("DINNERS");
		brandnames.add("AMEX");

		result = new ModelAndView("application/updateCreate");
		result.addObject("application", application);
		result.addObject("estados", estados);
		result.addObject("comentarios", comentarios);
		result.addObject("brandnames", brandnames);
		result.addObject("requestURI", "application/customer/edit.do");

		result.addObject("message", messageCode);

		return result;
	}

}
