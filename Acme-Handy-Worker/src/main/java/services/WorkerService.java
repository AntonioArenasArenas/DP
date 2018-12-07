package services;

import java.util.Collection;
import java.util.LinkedList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.WorkerRepository;
import security.LoginService;
import security.UserAccount;
import domain.Box;
import domain.Message;
import domain.Worker;

@Service
@Transactional
public class WorkerService {

	@Autowired
	private WorkerRepository WorkerRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private BoxService boxService;

	// Constructors -----------------------------------------------------------

	public WorkerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Worker create() {
		Worker result;
		UserAccount userAccount;
		Collection<Box> box;
		box = boxService.createDefault();

		result = new Worker();
		userAccount = new UserAccount();

		result.setUserAccount(userAccount);
		result.setBoxes(box);
		result.setMake(result.getName() + result.getSurname());
		result.setReceivedMessages(new LinkedList<Message>());
		result.setSentMessages(new LinkedList<Message>());

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

	public Worker save(Worker Worker) {
		Assert.notNull(Worker);

		Worker result;

		result = WorkerRepository.save(Worker);
		result.setMake(result.getName() + result.getSurname());

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
