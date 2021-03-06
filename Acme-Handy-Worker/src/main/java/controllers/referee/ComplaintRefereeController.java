package controllers.referee;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ComplaintService;
import controllers.AbstractController;
import domain.Complaint;


	
	@Controller
	@RequestMapping("/complaint/referee")
	public class ComplaintRefereeController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private ComplaintService	complaintService;


	//Constructor -------------------------------------------------------------

	public ComplaintRefereeController() {
		super();
	}
		// Listing ----------------------------------------------------------------

		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list() {
			ModelAndView result;
			Collection<Complaint> complaints;
			
			complaints = complaintService.findAll();

			
			result = new ModelAndView("complaint/list");
			result.addObject("requestURI", "complaint/referee/list.do");
			result.addObject("complaints", complaints);

			return result;
		}

	/*	// Creation ---------------------------------------------------------------

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
			result = this.createEditModelAndView(task);

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
		}*/
		
		// Showing -----------------------------------------------------------------
		
		@RequestMapping(value = "/show", method = RequestMethod.GET)
		public ModelAndView show(@RequestParam final int id) {
			ModelAndView result;
			Complaint complaint;

			complaint = this.complaintService.findOne(id);
			Assert.notNull(complaint);
			result = this.showModelAndView(complaint);

			return result;
		}

		// Ancillary methods ------------------------------------------------------

	/*	protected ModelAndView createEditModelAndView(final Task task) {
			ModelAndView result;

			result = this.createEditModelAndView(task, null);

			return result;
		}

		protected ModelAndView createEditModelAndView(final Task task, final String message) {
			ModelAndView result;
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

			return result;
		} */
		
		protected ModelAndView showModelAndView(final Complaint complaint){
			ModelAndView result;
			result = new ModelAndView("complaint/show");
			result.addObject("complaint", complaint);
			
			return result;

	}
}