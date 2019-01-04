package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.TaskService;
import services.WorkerService;
import domain.Application;

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

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int taskId) {
		ModelAndView result;
		Application application;

		application = this.applicationService.createApplication(taskService
				.findOne(taskId));
		Assert.notNull(application);
		result = null;
		// result = createEditModelAndView(application);

		return result;

	}
}
