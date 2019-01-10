package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PhaseRepository;
import domain.Application;
import domain.Phase;
import domain.Task;
import domain.Worker;

@Service
@Transactional
public class PhaseService {

	@Autowired
	private PhaseRepository phaseRepository;

	@Autowired
	private WorkerService workerService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private TaskService taskService;

	public Collection<Phase> findAll() {

		Collection<Phase> result;
		
		result= phaseRepository.findAll();
		
		return result;
	}
	
	public Phase findOne(int phaseId) {
		Assert.isTrue(phaseId != 0);

		Phase result;

		result = phaseRepository.findOne(phaseId);
		Assert.notNull(result);

		return result;
	}

	public Phase createPhase() {

		Phase result;

		result = new Phase();

		return result;
	}

	public Phase save(Phase phase, Task task) {

		Assert.notNull(phase);
		
		Assert.notNull(task);
		
		Worker worker = workerService.findByPrincipal();

		Assert.isTrue(!applicationService.getWorkerAcceptedApplicationsByTaskId(worker.getId(), task.getId()).isEmpty()); // This checks if the worker attempting to make a phase for a task has an accepted application for that task
		
		Assert.isTrue(phase.getStartDate().before(phase.getEndDate()));
		
		Phase result;

		result = phaseRepository.save(phase);
		
		if(phase.getId() == 0) {
			task.getPhases().add(result);
//			taskService.save(task);
		}

		return result;
	}
}
