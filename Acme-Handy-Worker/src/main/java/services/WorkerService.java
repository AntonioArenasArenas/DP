package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.WorkerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Actor;
import domain.Application;
import domain.Box;
import domain.Endorsement;
import domain.Message;
import domain.Profile;
import domain.Tutorial;
import domain.Worker;

@Service
@Transactional
public class WorkerService {

	@Autowired
	private WorkerRepository WorkerRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private BoxService boxService;
	
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public WorkerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Worker create() {
		Worker result;
		result = new Worker();
		UserAccount userAccount;
		userAccount = new UserAccount();
		Collection<Profile> profiles = new ArrayList<Profile>();
		Collection<Box> box;
		box = boxService.createDefault();
		
		
		List<Authority> ls;
		ls = new ArrayList<>();
		Authority authority;
		authority = new Authority();
		authority.setAuthority(Authority.WORKER);
		ls.add(authority);
		
		userAccount.setAuthorities(ls);	

		result.setUserAccount(userAccount);
		result.setBoxes(box);
		result.setMake("");
		result.setProfiles(profiles);
		result.setReceivedMessages(new LinkedList<Message>());
		result.setSentMessages(new LinkedList<Message>());
		result.setEndorsements(new LinkedList<Endorsement>());
		result.setApplications(new LinkedList<Application>());
		result.setTutorials(new LinkedList<Tutorial>());
		

		return result;
	}

	public Collection<Worker> findAll() {
		Collection<Worker> result;

		result = WorkerRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Worker findOne(int WorkerId) {
		Worker result;

		result = WorkerRepository.findOne(WorkerId);
		Assert.notNull(result);

		return result;
	}

	public Worker save(Worker worker) {
		Assert.notNull(worker);
		
		Assert.isTrue(worker.getEmail().matches("^([A-z0-9 ]{1,}<[A-z0-9]{1,}@[A-z0-9.]{1,}>|[A-z0-9]{1,}@[A-z0-9.]{1,})$")); // I do this here because admins can also have an email without domain (abcdef@) but the rest of users can't
		
		if(worker.getId()!=0){
			Actor actor = actorService.findByPrincipal();
			Assert.isTrue(actor.equals(worker));
		}
		
		String phoneNumber = worker.getPhoneNumber();
		if(!phoneNumber.startsWith("+")) {
			worker.setPhoneNumber("+34" + phoneNumber);  // TODO: Coger prefijo de systemData
		}


		Worker result;
		worker.setMake(worker.getName() + worker.getSurname());
		result = WorkerRepository.save(worker);
		

		return result;
	}

	// Other business methods -------------------------------------------------

	public Worker findByPrincipal() {
		Worker result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Worker findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);

		Worker result;

		result = WorkerRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

}
