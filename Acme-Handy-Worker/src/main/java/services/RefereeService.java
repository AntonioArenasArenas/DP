package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RefereeRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Box;
import domain.Message;
import domain.Profile;
import domain.Referee;

@Service
@Transactional
public class RefereeService {
	
	@Autowired
	private RefereeRepository	refereeRepository;
	
	
	// Supporting services ----------------------------------------------------
	@Autowired
	private BoxService	boxService;


	// Constructors -----------------------------------------------------------

	public RefereeService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	
	public Referee create() {
		Referee result;
		UserAccount userAccount;
		Authority au = new Authority();
		au.setAuthority(Authority.REFEREE);
		Collection<Authority> authorities = new ArrayList<>();
		authorities.add(au);
		Collection<Box> box;
		box = boxService.createDefault();

		result = new Referee();
		userAccount = new UserAccount();

		userAccount.setAuthorities(authorities);
		Collection<Profile> profiles = new ArrayList<>();

		result.setProfiles(profiles);
		result.setUserAccount(userAccount);
		result.setBoxes(box);
		result.setReceivedMessages(new LinkedList<Message>());
		result.setSentMessages(new LinkedList<Message>());

		return result;
	}

	public Collection<Referee> findAll() {
		Collection<Referee> result;

		result = refereeRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Referee findOne(int refereeId) {
		Referee result;

		result = refereeRepository.findOne(refereeId);
		Assert.notNull(result);

		return result;
	}

	public Referee save(Referee referee) {
		Assert.notNull(referee);

		Referee result;

		result = refereeRepository.save(referee);

		return result;
	}

	public void delete(Referee referee) {
		Assert.notNull(referee);
		Assert.isTrue(referee.getId() != 0);

		refereeRepository.delete(referee);
	}

	// Other business methods -------------------------------------------------

	public Referee findByPrincipal() {
		
		Referee result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Referee findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);

		Referee result;

		result = refereeRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

	
}
