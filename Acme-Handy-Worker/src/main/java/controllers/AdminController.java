package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccount;
import security.UserAccountService;
import services.ActorService;
import services.AdminService;
import domain.Admin;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

	
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private ActorService actorService;

	
	// Constructors -----------------------------------------------------------

	public AdminController() {
		super();
	}
	
	// Edit
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Admin admin;

		admin = this.adminService.create() ;
		result = this.createEditModelAndView(admin);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
	
		Admin admin = adminService.findByPrincipal();
		Assert.notNull(admin);
		result = this.createEditModelAndView(admin);

		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Admin admin, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()){
			
			result = this.createEditModelAndView(admin);
			System.out.println(binding.getAllErrors());
			
			}
		else
			try {	
				
				UserAccount userAccount = admin.getUserAccount();
				final String password = new Md5PasswordEncoder().encodePassword(userAccount.getPassword(), null);
				userAccount.setPassword(password);
				userAccount = this.userAccountService.save(userAccount);
				admin.setUserAccount(userAccount);
				
				this.adminService.save(admin);
				result = new ModelAndView("redirect:welcome.do");
				
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(admin, "admin.commit.error");
		}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

				protected ModelAndView createEditModelAndView(final Admin admin) {
					ModelAndView result;

					result = this.createEditModelAndView(admin, null);

					return result;
				}

				protected ModelAndView createEditModelAndView(final Admin admin, final String messageError) {
					ModelAndView result;

					result = new ModelAndView("admin/edit");
					result = new ModelAndView("admin/create");
					result.addObject("admin", admin);
					result.addObject("message", messageError);

					return result;
				}

}
