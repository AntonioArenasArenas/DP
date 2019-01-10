package controllers.worker;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.TaskService;
import controllers.AbstractController;
import domain.Task;

@Controller
@RequestMapping("/task/worker")
public class TaskWorkerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private TaskService taskService;

	// Constructors -----------------------------------------------------------

	public TaskWorkerController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Task> tasks;

		tasks = this.taskService.getActiveTasks();
		result = new ModelAndView("task/list");
		result.addObject("requestURI", "task/worker/list.do");
		result.addObject("tasks", tasks);

		return result;
	}
	
	// Showing -----------------------------------------------------------------
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int id) {
		ModelAndView result;
		Task task;

		task = this.taskService.findOne(id);
		Assert.notNull(task);
		result = this.showModelAndView(task);
		
		return result;
	}

	// Ancillary methods ------------------------------------------------------
	
	protected ModelAndView showModelAndView(final Task task) {
		ModelAndView result;
		result = new ModelAndView("task/show");
		result.addObject("task", task);

		return result;
	}

}
