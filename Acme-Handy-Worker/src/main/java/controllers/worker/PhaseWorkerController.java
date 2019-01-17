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
import services.SystemDataService;
import services.TaskService;
import services.WorkerService;
import controllers.AbstractController;
import domain.Phase;
import domain.SystemData;
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
	
	@Autowired
	private SystemDataService systemDataService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int taskId) {
		ModelAndView result;

		Collection<Phase> phases;

		Task task = taskService.findOne(taskId);
		phases = task.getPhases();
		
		Worker worker = workerService.findByPrincipal();
		
		if(applicationService.getWorkerAcceptedApplicationsByTaskId(worker.getId(), task.getId()).isEmpty()){
			result = new ModelAndView("redirect:/");
		} else {
			result = new ModelAndView("phase/list");
			result.addObject("phases", phases);
			SystemData data = (SystemData) systemDataService.findAll().toArray()[0];
			result.addObject("data", data);

			result.addObject("requestURI", "phase/worker/list.do");
		}
		
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
		
		Worker loggedWorker = workerService.findByPrincipal();
		Task task = phaseService.getTaskByPhaseId(id);
		if(!taskService.getTasksWithAcceptedApplicationsByWorkerId(loggedWorker.getId()).contains(task)) {
			result = new ModelAndView("redirect:/");
		} else {
			result = new ModelAndView("phase/show");
			result.addObject("phase", phase);
		}

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
		
		Worker loggedWorker = workerService.findByPrincipal();
		Task task = phaseService.getTaskByPhaseId(id);
		if(!taskService.getTasksWithAcceptedApplicationsByWorkerId(loggedWorker.getId()).contains(task)) {
			result = new ModelAndView("redirect:/");
		} else {
			result = this.createEditModelAndView(phase);
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Phase phase, final BindingResult binding, @RequestParam int taskId) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = this.createEditModelAndView(phase);
		} else {
			try {
				this.phaseService.save(phase, taskService.findOne(taskId));
				result = new ModelAndView("redirect:list.do?taskId=" + taskId);
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(phase, "phase.commit.error");
			}
		}

		return result;

	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Phase phase, final BindingResult binding, @RequestParam int taskId) {
		ModelAndView result;

		try {
			this.phaseService.delete(phase);
			result = new ModelAndView("redirect:list.do?taskId=" + taskId);
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(phase, "phase.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(Phase phase) {
		ModelAndView result;
		result = this.createEditModelAndView(phase, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Phase phase,
			String messageCode) {
		ModelAndView result;
		SystemData data = (SystemData) systemDataService.findAll().toArray()[0];
		if(phase.getId() == 0) {
			result = new ModelAndView("phase/create");
		} else {
			result = new ModelAndView("phase/edit");
		}
		result.addObject("phase", phase);
		result.addObject("message", messageCode);
		result.addObject("data", data);

		return result;
	}
}
