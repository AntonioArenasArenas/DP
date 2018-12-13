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
import domain.Referee;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RefereeServiceTest extends AbstractTest {
	// Supporting services ----------------------------------------------------
	@Autowired
	private RefereeService refereeService;
	@Autowired
	private UserAccountService userAccountService;

	// Test ----------------------------------------------------

	@Test
	public void testCreateReferee() {

		Referee referee = refereeService.create();
		UserAccount acc = userAccountService.create();
		Authority au = new Authority();
		au.setAuthority(Authority.REFEREE);

		acc.getAuthorities().add(au);
		acc.setPassword("password");
		acc.setUsername("username");

		UserAccount acc_saved = userAccountService.save(acc);
		referee.setUserAccount(acc_saved);

		Referee saved = refereeService.save(referee);

		Assert.notNull(saved);
		Assert.isTrue(saved.getBoxes().size() == 4);

	}

	@Test
	public void testCreate() {
		final Referee c = this.refereeService.create();
		Assert.notNull(c);
		final Authority auth = (Authority) c.getUserAccount().getAuthorities()
				.toArray()[0];
		Assert.isTrue(auth.getAuthority().equals("REFEREE"));

	}

	@Test
	public void testFindAll() {
		Collection<Referee> referees = refereeService.findAll();

		Assert.isTrue(referees.size() == 3);
	}

}
