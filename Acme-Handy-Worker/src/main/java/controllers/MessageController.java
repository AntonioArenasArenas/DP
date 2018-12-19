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

import services.MessageService;

import controllers.AbstractController;
import domain.Box;
import domain.Customer;
import domain.Message;
import domain.Worker;

@Controller
@RequestMapping("/message")
public class MessageController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private MessageService messageService;

	// Constructors -----------------------------------------------------------

	public MessageController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Message> messages = this.messageService.findAll();

		result = new ModelAndView("message/list");
		result.addObject("requestURI", "message/list.do");
		result.addObject("messages", messages);

		return result;
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Message message;

		message = this.messageService.createMessage() ;
		result = this.createEditModelAndView(message);

		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Message message, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(message);
		else
			try {
				this.messageService.save(message) ;
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(message, "message.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Message message,final Box box, final BindingResult binding) {
		ModelAndView result;

		try {
			
			this.messageService.delete(message,box);
			
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(message, "announcement.commit.error");
		}

		return result;
	}

	
	// Ancillary methods ------------------------------------------------------

			protected ModelAndView createEditModelAndView(final Message message) {
				ModelAndView result;

				result = this.createEditModelAndView(message, null);

				return result;
			}

			protected ModelAndView createEditModelAndView(final Message message, final String messag) {
				ModelAndView result;
				


				result = new ModelAndView("message/edit");
				result.addObject("message", message);

				return result;
			}

}