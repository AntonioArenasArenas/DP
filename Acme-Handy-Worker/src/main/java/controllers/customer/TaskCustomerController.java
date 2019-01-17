package controllers.customer;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import services.CustomerService;
import services.SystemDataService;
import services.TaskService;
import services.WarrantyService;
import controllers.AbstractController;
import domain.Category;
import domain.Customer;
import domain.SystemData;
import domain.Task;
import domain.Warranty;

@Controller
@RequestMapping("/task/customer")
public class TaskCustomerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private WarrantyService warrantyService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private SystemDataService systemDataService;

	// Constructors -----------------------------------------------------------

	public TaskCustomerController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Task> tasks;

		tasks = this.taskService.getTasksByLogged();
		result = new ModelAndView("task/list");
		SystemData data = (SystemData) systemDataService.findAll().toArray()[0];
		result.addObject("data", data);

		result.addObject("requestURI", "task/customer/list.do");
		result.addObject("tasks", tasks);

		return result;
	}
	
	@RequestMapping(value = "/listFinder", method = RequestMethod.GET)
	public ModelAndView listFinder(Collection<Task> tasks) {
		ModelAndView result;

		result = new ModelAndView("task/list");
		SystemData data = (SystemData) systemDataService.findAll().toArray()[0];
		result.addObject("data", data);

		result.addObject("requestURI", "task/customer/list.do");
		result.addObject("tasks", tasks);

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Task task;

		task = this.taskService.create();
		result = this.createEditModelAndView(task);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {
		ModelAndView result;
		Task task;

		task = this.taskService.findOne(id);
		Assert.notNull(task);
		
		Customer loggedCustomer = customerService.findByPrincipal();
		if(task.getCustomer() != loggedCustomer) {
			result = new ModelAndView("redirect:list.do");
		} else {
			result = this.createEditModelAndView(task);
		}
		
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Task task, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(task);
		else
			try {
				this.taskService.save(task);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(task, "task.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Task task, final BindingResult binding) {
		ModelAndView result;

		try {
			this.taskService.delete(task);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(task, "task.commit.error");
		}

		return result;
	}
	
	// Showing -----------------------------------------------------------------
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int id) {
		ModelAndView result;
		Task task;

		task = this.taskService.findOne(id);
		Assert.notNull(task);

		Customer loggedCustomer = customerService.findByPrincipal();
		if(task.getCustomer() != loggedCustomer) {
			result = new ModelAndView("redirect:list.do");
		} else {
			result = this.showModelAndView(task);
		}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Task task) {
		ModelAndView result;

		result = this.createEditModelAndView(task, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Task task, final String message) {
		ModelAndView result;
		SystemData data = (SystemData) systemDataService.findAll().toArray()[0];
		if(task.getId() == 0) {
			result = new ModelAndView("task/create");
		} else {
			result = new ModelAndView("task/edit");
		}
		Collection<Warranty> warranties = this.warrantyService.getFinalWarranties();
		Collection<Category> categories = this.categoryService.findAll();
		result.addObject("warranties", warranties);
		result.addObject("categories", categories);
		result.addObject("task", task);
		result.addObject("message", message);
		result.addObject("data", data);
		return result;
	}
	
	protected ModelAndView showModelAndView(final Task task) {
		ModelAndView result;
		result = new ModelAndView("task/show");
		result.addObject("task", task);

		return result;
	}

}
