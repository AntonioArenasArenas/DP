package controllers.worker;

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

import services.ApplicationService;
import services.PhaseService;
import services.TaskService;
import services.WorkerService;
import controllers.AbstractController;
import domain.Phase;
import domain.Task;
import domain.Worker;

@Controller
@RequestMapping("/phase/worker")
public class PhaseWorkerController extends AbstractController {

	@Autowired
	private PhaseService phaseService;

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private WorkerService workerService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int taskId) {
		ModelAndView result;

		Collection<Phase> phases;

		Task task = taskService.findOne(taskId);
		phases = task.getPhases();
		
		Worker worker = workerService.findByPrincipal();
		
		boolean showCreateButton = false;
		if(!applicationService.getWorkerAcceptedApplicationsByTaskId(worker.getId(), task.getId()).isEmpty()) {
			showCreateButton = true;
		}
		
		result = new ModelAndView("phase/list");
		result.addObject("phases", phases);
		result.addObject("showCreateButton", showCreateButton);
		result.addObject("requestURI", "phase/worker/list.do");

		return result;

	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam int id) {
		ModelAndView result;

		Phase phase;

		try {
			phase = phaseService.findOne(id);
		} catch (Exception e) {
			return result = new ModelAndView("redirect:list.do");
		}
//		Worker worker = workerService.findByPrincipal();
//
//		if (!phase.getWorker().equals(worker)) {
//			return result = new ModelAndView("redirect:list.do");
//		}

		result = new ModelAndView("phase/show");
		result.addObject("phase", phase);

		return result;

	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Phase phase;

		phase = this.phaseService.createPhase();
		Assert.notNull(phase);
		result = null;
		result = this.createEditModelAndView(phase);

		return result;

	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {
		ModelAndView result;
		Phase phase;

		phase = this.phaseService.findOne(id);
		Assert.notNull(phase);
		result = this.createEditModelAndView(phase);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Phase phase, @RequestParam int taskId,
			BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(phase);
		} else {
//			try {

				phaseService.save(phase, taskService.findOne(taskId));
				result = new ModelAndView("redirect:list.do?taskId=" + taskId);
//			} catch (Throwable oops) {
//				result = createEditModelAndView(phase,
//						"phase.commit.error");
//
//			}
		}

		return result;

	}

	protected ModelAndView createEditModelAndView(Phase phase) {
		ModelAndView result;
		result = createEditModelAndView(phase, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Phase phase,
			String messageCode) {
		ModelAndView result;
		if(phase.getId() == 0) {
			result = new ModelAndView("phase/create");
		} else {
			result = new ModelAndView("phase/edit");
		}
		result.addObject("phase", phase);
		result.addObject("message", messageCode);

		return result;
	}
}
