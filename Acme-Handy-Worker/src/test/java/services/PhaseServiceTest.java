package services;

import java.util.Collection;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Application;
import domain.Phase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class PhaseServiceTest extends AbstractTest {

	@Autowired
	private PhaseService phaseService;

	@Autowired
	private ApplicationService applicationService;

	@Test
	public void testCreatePhase() {

		Phase phase, saved;
		Application application = new Application();
		Collection<Application> applications = applicationService.findAll();
		Iterator<Application> it = applications.iterator();
		boolean exit = false;
		while (!exit && it.hasNext()) {
			Application a = it.next();
			if (a.getStatus().equals("ACCEPTED")) {
				application = a;
				exit = true;
			}
		}

		super.authenticate(application.getWorker().getUserAccount()
				.getUsername());

		phase = phaseService.createPhase(application);

		phase.setTitle("Titulo phase");
		phase.setDescription("Descripcion phase");
		saved = phaseService.save(phase);

		Collection<Phase> phases = phaseService.findAll();

		Assert.isTrue(phases.contains(saved));

		super.authenticate(null);

	}
}
