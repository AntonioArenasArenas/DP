package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Box;
import domain.Customer;
import domain.Message;
import domain.Profile;
import domain.Task;

@Service
@Transactional
public class CustomerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CustomerRepository customerRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private BoxService boxService;

	// Constructors -----------------------------------------------------------

	public CustomerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Customer create() {
		Customer result;
		UserAccount userAccount;
		Authority au = new Authority();
		au.setAuthority(Authority.CUSTOMER);
		Collection<Authority> authorities = new ArrayList<>();
		authorities.add(au);
		Collection<Box> box;
		box = boxService.createDefault();

		result = new Customer();
		userAccount = new UserAccount();

		userAccount.setAuthorities(authorities);
		Collection<Profile> profiles = new ArrayList<>();
		Collection<Task> tasks = new ArrayList<>();

		result.setTasks(tasks);
		result.setProfiles(profiles);
		result.setUserAccount(userAccount);
		result.setBoxes(box);
		result.setReceivedMessages(new LinkedList<Message>());
		result.setSentMessages(new LinkedList<Message>());

		return result;
	}

	public Collection<Customer> findAll() {
		Collection<Customer> result;

		result = customerRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Customer findOne(int customerId) {
		Customer result;

		result = customerRepository.findOne(customerId);
		Assert.notNull(result);

		return result;
	}

	public Customer save(Customer customer) {
		Assert.notNull(customer);

		Customer result;

		result = customerRepository.save(customer);

		return result;
	}

	public void delete(Customer customer) {

		Assert.notNull(customer);
		Assert.isTrue(customer.getId() != 0);

		customerRepository.delete(customer);
	}

	// Other business methods -------------------------------------------------

	public Customer findByPrincipal() {
		Customer result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Customer findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);

		Customer result;

		result = customerRepository.findByUserAccountId(userAccount.getId());

		return result;
	}

}