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
import services.TaskService;
import services.WorkerService;
import domain.Application;
import domain.Worker;

@Controller
@RequestMapping("/application/worker")
public class ApplicationWorkerController extends AbstractController {

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private WorkerService workerService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<Application> applications;

		applications = applicationService.findWorkerApplications();

		result = new ModelAndView("application/list");
		result.addObject("applications", applications);
		result.addObject("requestURI", "application/worker/list.do");

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
		Worker worker = workerService.findByPrincipal();

		if (!application.getWorker().equals(worker)) {
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

		return result;

	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int taskId) {
		ModelAndView result;
		Application application;

		application = this.applicationService.createApplication(taskService
				.findOne(taskId));
		Assert.notNull(application);
		result = null;
		result = this.createEditModelAndView(application);

		return result;

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Application application,
			BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(application);
		} else {
			try {

				applicationService.save(application);
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

		result = new ModelAndView("application/updateCreate");
		result.addObject("application", application);
		result.addObject("message", messageCode);

		return result;
	}
}
