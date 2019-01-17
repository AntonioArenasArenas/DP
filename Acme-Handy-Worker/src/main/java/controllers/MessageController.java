package controllers;

import java.util.Collection;


import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.MessageService;
import services.SystemDataService;

import controllers.AbstractController;
import domain.Actor;
import domain.Box;
import domain.SystemData;

import domain.Message;


@Controller
@RequestMapping("/message")
public class MessageController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private MessageService messageService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private SystemDataService systemDataService;

	// Constructors -----------------------------------------------------------

	public MessageController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listFromBox(@RequestParam final Integer boxId) {
		ModelAndView result;
		Collection<Message> messages = this.messageService.findMessagesByBoxId(boxId) ;

		result = new ModelAndView("message/list");
		SystemData data = (SystemData) systemDataService.findAll().toArray()[0];
		result.addObject("data", data);

		result.addObject("requestURI", "message/list.do");
		result.addObject("messages", messages);

		return result;
	}
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final Integer messageId) {
		ModelAndView result;
		Message mensaje = this.messageService.findOne(messageId) ;

		result = new ModelAndView("message/show");
		result.addObject("requestURI", "message/show.do");
		result.addObject("mensaje", mensaje);

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

		if (binding.hasErrors()){
			
			result = this.createEditModelAndView(message);
			System.out.println(binding.getAllErrors());}
		
		else
			try {	
				
				this.messageService.save(message) ;
				result = new ModelAndView("redirect:/");
				
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(message, "message.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET )
	public ModelAndView delete(@RequestParam int messageId) {
		ModelAndView result;
		
		Message message = this.messageService.findOne(messageId);
			
			this.messageService.delete(message);
			
			result = new ModelAndView("redirect:/");
	
			return result;
	}
	
	

	
	// Ancillary methods ------------------------------------------------------

			protected ModelAndView createEditModelAndView(final Message message) {
				ModelAndView result;

				result = this.createEditModelAndView(message, null);

				return result;
			}

			protected ModelAndView createEditModelAndView(final Message message, final String messageError) {
				ModelAndView result;
				Collection<Actor> recipients = actorService.findAll();
				SystemData data = (SystemData) systemDataService.findAll().toArray()[0];

				result = new ModelAndView("message/edit");
				result.addObject("messaged", message);
				result.addObject("recipients", recipients);
				result.addObject("message", messageError);
				result.addObject("data", data);

				return result;
			}

}