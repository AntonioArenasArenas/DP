package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;

import domain.Actor;



@Service
@Transactional
public class ActorService {
	
	// Managed repository -----------------------------------------------------

	@Autowired
	private ActorRepository		actorRepository;

	// Supporting services ----------------------------------------------------
	

	// Constructors -----------------------------------------------------------

	public ActorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Collection<Actor> findAll() {
		Collection<Actor> result;

		result = actorRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Actor findOne(int actorId) {
		Assert.isTrue(actorId != 0);

		Actor result;

		result = actorRepository.findOne(actorId);
		Assert.notNull(result);

		return result;
	}

	public Actor save(Actor actor) {
		
		
		Assert.notNull(actor);

		Actor result;
		
		String phoneNumber = actor.getPhoneNumber();
		if(!phoneNumber.startsWith("+")) {
			actor.setPhoneNumber("+34" + phoneNumber);  // TODO: Coger prefijo de systemData
		}

		result = actorRepository.save(actor);

		return result;
	}

	// Other business methods -------------------------------------------------
	
	public Actor findByPrincipal() {
		Actor result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Actor findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);

		Actor result;

		result = actorRepository.findByUserAccountId(userAccount.getId());

		return result;
	}
	
	public Collection<Actor> getSuspiciousActors() {
		
		return actorRepository.getSuspiciousActors();
		
	}
	
	public Actor banActor(Actor actor) {
		
		Assert.notNull(actor);
		
		Assert.isTrue(actor.isSuspicious() == true);
		
		actor.getUserAccount().setActivated(false);
		
		Actor result = actorRepository.save(actor);

		return result;
		
	}
	
	public Actor unbanActor(Actor actor) {
		
		Assert.notNull(actor);
		
		actor.getUserAccount().setActivated(true);
		
		Actor result = actorRepository.save(actor);

		return result;
		
	}
	

}
