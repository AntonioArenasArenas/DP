package security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;

@Service
@Transactional
public class UserAccountService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private UserAccountRepository userAccountRepository;

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public UserAccountService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Collection<UserAccount> findAll() {
		Collection<UserAccount> result;

		result = userAccountRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public UserAccount findOne(int userAccountId) {
		Assert.isTrue(userAccountId != 0);

		UserAccount result = userAccountRepository.findOne(userAccountId);

		Assert.notNull(result);

		return result;
	}

	public UserAccount create() {
		UserAccount result;
		result = new UserAccount();

		Collection<Authority> authorities = new ArrayList<>();
		result.setAuthorities(authorities);

		return result;
	}

	public UserAccount findByActor(final Actor actor) {
		Assert.notNull(actor);

		UserAccount result;

		result = this.userAccountRepository.findByActorId(actor.getId());

		return result;
	}

	public UserAccount save(UserAccount userAccount) {
		Assert.notNull(userAccount);

		UserAccount result;

		result = userAccountRepository.save(userAccount);

		return result;
	}

	// Other business methods -------------------------------------------------

}
