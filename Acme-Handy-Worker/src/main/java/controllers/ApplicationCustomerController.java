package controllers;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.CreditCardService;
import services.CustomerService;
import services.TaskService;
import domain.Application;
import domain.CreditCard;
import domain.Customer;

@Controller
@RequestMapping("/application/customer")
public class ApplicationCustomerController extends AbstractController {

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private TaskService taskService;

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
		// TODO Aquí hacer tratamiento de dividir lo recibido entre 100 y
		// pasarlo
		result.addObject("VAT", 0.21);

		return result;

	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam int applicationId) {
		ModelAndView result;

		Application application;

		try {
			application = applicationService.findOne(applicationId);

		} catch (Exception e) {
			return result = new ModelAndView("redirect:list.do");
		}

		Customer c = customerService.findByPrincipal();

		if (!taskService.getTasksByCustomerId(c.getId()).contains(
				application.getTask())) {
			return result = new ModelAndView("redirect:list.do");
		}

		Collection<String> comentarios = new LinkedList<String>();
		if (application.getComments() != null) {
			String[] spliteado = application.getComments().split(";");
			comentarios = Arrays.asList(spliteado);
		}

		result = new ModelAndView("application/show");
		result.addObject("application", application);
		result.addObject("comentarios", comentarios);
		// TODO Aquí hacer tratamiento de dividir lo recibido entre 100 y
		// pasarlo
		result.addObject("VAT", 0.21);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int applicationId) {
		ModelAndView result;
		Application application;

		try {
			application = applicationService.findOne(applicationId);

		} catch (Exception e) {
			return result = new ModelAndView("redirect:list.do");
		}
		Customer c = customerService.findByPrincipal();

		if (!taskService.getTasksByCustomerId(c.getId()).contains(
				application.getTask())) {
			return result = new ModelAndView("redirect:list.do");
		}

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
					CreditCard persisted = creditCardService.save(application
							.getCreditCard());
					application.setCreditCard(persisted);
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

	protected ModelAndView createEditModelAndView(Application application,
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
		
		//TODO aqui coger los brandnamesF
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

		// TODO Aquí hacer tratamiento de dividir lo recibido entre 100 y
		// pasarlo
		result.addObject("VAT", 0.21);

		result.addObject("message", messageCode);

		return result;
	}

}
