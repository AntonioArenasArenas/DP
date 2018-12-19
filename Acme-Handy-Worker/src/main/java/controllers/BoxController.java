package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BoxService;
import domain.Box;


@Controller
@RequestMapping("/box")
public class BoxController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private BoxService boxService;

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

}