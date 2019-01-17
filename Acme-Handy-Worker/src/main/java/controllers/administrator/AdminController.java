package controllers.administrator;

import java.util.Collection;

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
import services.AdminService;
import services.ApplicationService;
import services.CategoryService;
import services.SystemDataService;
import services.TaskService;
import controllers.AbstractController;
import domain.Admin;
import domain.SystemData;
import domain.Worker;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SystemDataService systemDataService;

	// Constructors -----------------------------------------------------------

	public AdminController() {
		super();
	}

	// Action-1 ---------------------------------------------------------------

	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	public ModelAndView statistics() {
		ModelAndView result;
		
		Double[] spuser = new Double[4];

		Double[] sptask = new Double[4];

		Double[] spoffered = new Double[4];
		
		if(!taskService.findAll().isEmpty()) {
			spuser = taskService.tasksPerUserStatistics();

			sptask = applicationService.getAdminStatisticsPerTask();

			spoffered = applicationService
					.getAdminStatisticsPriceOffered();
		}

		Double cantChange = applicationService.getAppliCantChange();

		Double pending = applicationService.getPendApplications();

		Double accepted = applicationService.getAcepApplications();

		Double rejected = applicationService.getRejecApplications();

		Collection<Worker> workersAVG = applicationService
				.getWorkersAcceptedMAvgApplications();

		result = new ModelAndView("admin/statistics");
		
		SystemData data = (SystemData) systemDataService.findAll().toArray()[0];
		result.addObject("data", data);


		 result.addObject("maximumpu", spuser[2]);
		 result.addObject("minimumpu", spuser[1]);
		 result.addObject("averagepu", spuser[0]);
		 result.addObject("stdevpu", spuser[3]);

		result.addObject("maximumpt", sptask[2]);
		result.addObject("minimumpt", sptask[1]);
		result.addObject("averagept", sptask[0]);
		result.addObject("stdevpt", sptask[3]);

		result.addObject("maximumpo", spoffered[2]);
		result.addObject("minimumpo", spoffered[1]);
		result.addObject("averagepo", spoffered[0]);
		result.addObject("stdevpo", spoffered[3]);

		result.addObject("ratio", cantChange);
		result.addObject("pending", pending);
		result.addObject("accepted", accepted);
		result.addObject("rejected", rejected);

		result.addObject("workers", workersAVG);

		return result;
	}

	// Edit

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Admin admin;

		admin = this.adminService.create();
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
	public ModelAndView save(@Valid final Admin admin,
			final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {

			result = this.createEditModelAndView(admin);
			System.out.println(binding.getAllErrors());

		} else
			try {

				UserAccount userAccount = admin.getUserAccount();
				if(admin.getId()==0){
					final String password = new Md5PasswordEncoder().encodePassword(userAccount.getPassword(), null);
					userAccount.setPassword(password);
				}
				userAccount = this.userAccountService.save(userAccount);
				admin.setUserAccount(userAccount);

				this.adminService.save(admin);
				result = new ModelAndView("redirect:/");

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(admin,
						"admin.commit.error");
			}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Admin admin) {
		ModelAndView result;

		result = this.createEditModelAndView(admin, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Admin admin,
			final String messageError) {
		ModelAndView result;
		SystemData data = (SystemData) systemDataService.findAll().toArray()[0];
		
		result = new ModelAndView("admin/edit");
		result = new ModelAndView("admin/create");
		result.addObject("admin", admin);
		result.addObject("message", messageError);
		result.addObject("data", data);
		

		return result;
	}

}
