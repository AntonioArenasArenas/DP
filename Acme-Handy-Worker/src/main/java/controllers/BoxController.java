package controllers;

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

import services.ActorService;
import services.BoxService;

import domain.Actor;
import domain.Box;


@Controller
@RequestMapping("/box")
public class BoxController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private BoxService boxService;
	
	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public BoxController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Box> boxes = this.boxService.findBoxesByPrincipal() ;

		result = new ModelAndView("box/list");
		result.addObject("requestURI", "box/list.do");
		result.addObject("boxes", boxes);

		return result;
	}
	
	// Edit
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Box box;

		box = this.boxService.create() ;
		result = this.createEditModelAndView(box);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int boxId) {
		ModelAndView result;
		
		Box box = this.boxService.findOne(boxId);
		Assert.notNull(box);
		result = this.createEditModelAndView(box);

		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Box box, final BindingResult binding) {
		ModelAndView result;
		
		if(box.getId()!=0){
			Actor logged = actorService.findByPrincipal();
			Assert.isTrue(logged.getBoxes().contains(box));
		}

		if (binding.hasErrors())
			result = this.createEditModelAndView(box);
		else
			try {
				this.boxService.save(box) ;
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(box, "box.commit.error");
		}

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET )
	public ModelAndView delete(@RequestParam int boxId) {
		ModelAndView result;
		
		Box box = this.boxService.findOne(boxId);
			
			this.boxService.delete(box);
			
			result = new ModelAndView("redirect:list.do");
	
			return result;
	}
	
	// Ancillary methods ------------------------------------------------------

				protected ModelAndView createEditModelAndView(final Box box) {
					ModelAndView result;

					result = this.createEditModelAndView(box, null);

					return result;
				}

				protected ModelAndView createEditModelAndView(final Box box, final String messageError) {
					ModelAndView result;


					result = new ModelAndView("box/edit");
					result = new ModelAndView("box/create");
					result.addObject("box", box);
					result.addObject("message", messageError);

					return result;
				}

}