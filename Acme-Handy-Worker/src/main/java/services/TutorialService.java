package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;



import repositories.TutorialRepository;
import domain.Actor;
import domain.Sponsorship;
import domain.Tutorial;
import domain.Worker;
import domain.Section;

@Service
@Transactional
public class TutorialService {

	@Autowired
	private TutorialRepository TutorialRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public TutorialService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Tutorial create() {
		Tutorial result;
		Collection<Sponsorship> sponsorships = new ArrayList<>();
		Collection<Section> sections = new ArrayList<>();

		result = new Tutorial();

		result.setSections(sections);
		result.setSponsorships(sponsorships);


		return result;
	}

	public Collection<Tutorial> findAll() {
		Collection<Tutorial> result;

		result = TutorialRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Tutorial findOne(int tutorialId) {
		Tutorial result;

		result = TutorialRepository.findOne(tutorialId);
		Assert.notNull(result);

		return result;
	}

	public Tutorial save(Tutorial tutorial) {
		Assert.notNull(tutorial);
		Date moment = new Date(System.currentTimeMillis() - 1);


		tutorial.setLastUpdate(moment);

		Tutorial result = TutorialRepository.save(tutorial);

		return result;
	}

	// Other business methods -------------------------------------------------

	public Collection<Tutorial> findByLogged() {
		Actor logged = actorService.findByPrincipal();
		Collection<Tutorial> result;

		result = TutorialRepository.getTutorialsByWorkerId(logged.getId());
		Assert.notNull(result);

		return result;
	}

	public Collection<Tutorial> findByActor(Worker w) {
		Collection<Tutorial> result;

		result = TutorialRepository.getTutorialsByWorkerId(w.getId());
		Assert.notNull(result);

		return result;
	}


}
