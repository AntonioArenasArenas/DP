package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PhaseRepository;
import domain.Application;
import domain.Phase;
import domain.Worker;

@Service
@Transactional
public class PhaseService {

	@Autowired
	private PhaseRepository phaseRepository;

	@Autowired
	private WorkerService workerService;

	public Collection<Phase> findAll() {

		Collection<Phase> result;
		
		result= phaseRepository.findAll();
		
		return result;
	}

	public Phase createPhase(Application a) {

		Phase result;

		Worker worker = workerService.findByPrincipal();

		Assert.isTrue(a.getStatus().equals("ACCEPTED"));

		Assert.isTrue(a.getWorker().equals(worker));

		result = new Phase();

		return result;
	}

	public Phase save(Phase phase) {

		Assert.notNull(phase);

		Phase result;

		// En el controlador se tendrá en cuenta el hecho de que las fechas no
		// puede ser menor ni mayor que la de la task

		result = phaseRepository.save(phase);

		return result;
	}
}
