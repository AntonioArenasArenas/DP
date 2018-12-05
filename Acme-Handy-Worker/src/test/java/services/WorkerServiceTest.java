package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import security.Authority;
import security.UserAccount;
import security.UserAccountService;
import utilities.AbstractTest;
import domain.Worker;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class WorkerServiceTest extends AbstractTest {

	@Autowired
	private WorkerService workerService;
	@Autowired
	private UserAccountService userAccountService;

	// Test ----------------------------------------------------

	@Test
	public void testCreateWorker() {

		Worker worker = workerService.create();
		UserAccount acc = userAccountService.create();
		Authority au = new Authority();
		au.setAuthority(Authority.WORKER);

		acc.getAuthorities().add(au);
		acc.setPassword("password");
		acc.setUsername("username");

		UserAccount acc_saved = userAccountService.save(acc);
		worker.setUserAccount(acc_saved);

		Worker saved = workerService.save(worker);

		Assert.notNull(saved);
		Assert.isTrue(saved.getBoxes().size() == 4);

	}

	@Test
	public void testFindAll() {
		Collection<Worker> workers = workerService.findAll();

		Assert.isTrue(workers.size() == 4);
	}

}
