package controllers.administrator;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.SystemDataService;
import controllers.AbstractController;
import domain.SystemData;
@Controller
@RequestMapping("/systemData/administrator")
public class SystemDataController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private SystemDataService systemDataService;

	// Constructors -----------------------------------------------------------

	public SystemDataController() {
		super();
	}

	// Listing ----------------------------------------------------------------


	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		SystemData systemData;

		systemData = this.systemDataService.create();
		result = this.createEditModelAndView(systemData);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		SystemData systemData;

		systemData = this.systemDataService.getSystemData();
		Assert.notNull(systemData);
		result = this.createEditModelAndView(systemData);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final SystemData systemData, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(systemData);
		else
			try {
				this.systemDataService.save(systemData);
				result = new ModelAndView("systemData/edit");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(systemData, "systemData.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final SystemData systemData, final BindingResult binding) {
		ModelAndView result;

		try {
			this.systemDataService.delete(systemData);
			result = new ModelAndView("redirect:/");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(systemData, "systemData.commit.error");
		}

		return result;
	}

	// Showing -----------------------------------------------------------------


	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final SystemData systemData) {
		ModelAndView result;

		result = this.createEditModelAndView(systemData, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final SystemData systemData, final String message) {
		ModelAndView result;
		if(systemData.getId() == 0) {
			result = new ModelAndView("systemData/create");
		} else {
			result = new ModelAndView("systemData/edit");
		}
		result.addObject("systemData", systemData);
		result.addObject("message", message);

		return result;
	}


}
