package controllers.worker;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import controllers.customer.TaskCustomerController;
import services.FinderService;
import services.SystemDataService;
import controllers.AbstractController;
import domain.Finder;
import domain.SystemData;
@Controller
@RequestMapping("/finder/worker")
public class FinderController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private FinderService finderService;
	@Autowired
	private TaskCustomerController taskCustomerController;
	@Autowired
	private SystemDataService systemDataService;

	// Constructors -----------------------------------------------------------

	public FinderController() {
		super();
	}

	// Listing ----------------------------------------------------------------


	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Finder finder;

		finder = this.finderService.create();
		result = this.createEditModelAndView(finder);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Finder finder;

		finder = this.finderService.getFinderByLoggedWorker();
		Assert.notNull(finder);
		result = this.createEditModelAndView(finder);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Finder finder, final BindingResult binding) {
		ModelAndView result;
		Finder finderDef;
		if (binding.hasErrors())
			result = this.createEditModelAndView(finder);
		else
			try {
				finderDef = this.finderService.save(finder);
				result = this.taskCustomerController.listFinder(finderDef.getTasks());
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(finder, "task.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Finder finder, final BindingResult binding) {
		ModelAndView result;

		try {
			this.finderService.delete(finder);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(finder, "task.commit.error");
		}

		return result;
	}

	// Showing -----------------------------------------------------------------

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int id) {
		ModelAndView result;
		Finder finder;

		finder = this.finderService.getFinderByLoggedWorker();
		Assert.notNull(finder);
		result = this.showModelAndView(finder);

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Finder finder) {
		ModelAndView result;

		result = this.createEditModelAndView(finder, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Finder finder, final String message) {
		ModelAndView result;
		SystemData data = (SystemData) systemDataService.findAll().toArray()[0];
		if(finder.getId() == 0) {
			result = new ModelAndView("finder/create");
		} else {
			result = new ModelAndView("finder/edit");
		}
		result.addObject("finder", finder);
		result.addObject("message", message);
		result.addObject("data", data);

		return result;
	}

	protected ModelAndView showModelAndView(final Finder finder) {
		ModelAndView result;
		SystemData data = (SystemData) systemDataService.findAll().toArray()[0];
		result = new ModelAndView("finder/edit");
		result.addObject("finder", finder);
		result.addObject("data", data);

		return result;
	}

}
