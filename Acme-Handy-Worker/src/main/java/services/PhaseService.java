package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PhaseRepository;
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
		
		Assert.isTrue(phase.getStartDate().after(task.getStartDate()) || phase.getStartDate().equals(task.getStartDate()));
		
		Assert.isTrue(phase.getEndDate().before(task.getEndDate()) || phase.getEndDate().equals(task.getEndDate()));
		
		Phase result;

		result = phaseRepository.save(phase);
		
		if(phase.getId() == 0) {
			task.getPhases().add(result);
		}

		return result;
	}
	
	public void delete(Phase phase) {
		Assert.notNull(phase);
		Task task = getTaskByPhaseId(phase.getId());
		
		Worker worker = workerService.findByPrincipal();
		Assert.isTrue(taskService.getTasksByWorkerId(worker.getId()).contains(task));
		
		task.getPhases().remove(phase);
		phaseRepository.delete(phase);
	}
	
	// Other business methods
	
	public Task getTaskByPhaseId(int phaseId) {
		return phaseRepository.getTaskByPhaseId(phaseId);
	}
}
