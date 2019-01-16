/*
 * ProfileController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

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

import domain.Actor;
import domain.Profile;

import services.ActorService;
import services.ProfileService;





@Controller
@RequestMapping("/profile")
public class ProfileController extends AbstractController {

	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private ActorService actorService;
	
	// Constructors -----------------------------------------------------------

		public ProfileController() {
			super();
		}
	
	// Listing ---------------------------------------------------------------		

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Profile> profiles = profileService.findProfiles(); ;
		
		result = new ModelAndView("profile/list");
		result.addObject("requestURI", "profile/list.do");
		result.addObject("profiles", profiles);

		return result;
	}
	
	// Deleting ---------------------------------------------------------------
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET )
	public ModelAndView delete(@RequestParam int profileId) {
		ModelAndView result;
		
		Profile profile = this.profileService.findOne(profileId);
			
			this.profileService.delete(profile);
			
			result = new ModelAndView("redirect:list.do");
	
			return result;
	}
	
	// Edit
	
		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create() {
			ModelAndView result;
			Profile profile;

			profile = this.profileService.create() ;
			result = this.createEditModelAndView(profile);

			return result;
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam final int profileId) {
			ModelAndView result;
	
			Profile profile = this.profileService.findOne(profileId);
			Assert.notNull(profile);
			result = this.createEditModelAndView(profile);

			return result;
		}
		
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid final Profile profile, final BindingResult binding) {
			ModelAndView result;
			
			if(profile.getId()!=0){
				Actor logged = actorService.findByPrincipal();
				Assert.isTrue(logged.getProfiles().contains(profile));
			}

			if (binding.hasErrors())
				result = this.createEditModelAndView(profile);
			else
				try {
					this.profileService.save(profile) ;
					result = new ModelAndView("redirect:list.do");
				} catch (final Throwable oops) {
					result = this.createEditModelAndView(profile, "profile.commit.error");
			}

			return result;
		}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Profile profile) {
		ModelAndView result;

		result = this.createEditModelAndView(profile, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Profile profile, final String messageError) {
		ModelAndView result;


		result = new ModelAndView("profile/edit");
		result.addObject("profile", profile);
		result.addObject("message", messageError);

		return result;
	}
	

}
