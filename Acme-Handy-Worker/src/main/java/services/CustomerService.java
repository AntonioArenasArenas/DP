package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Box;
import domain.Customer;
import domain.Endorsement;
import domain.Message;
import domain.Profile;

@Service
@Transactional
public class CustomerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CustomerRepository customerRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private BoxService boxService;
	
	@Autowired
	private UserAccountService userAccountService;

	// Constructors -----------------------------------------------------------

	public CustomerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Customer create() {
		Customer result;
		result = new Customer();
		UserAccount userAccount;
		userAccount = new UserAccount();
		Collection<Profile> profiles = new ArrayList<Profile>();
		Collection<Endorsement> endorserments = new ArrayList<Endorsement>();
		
		List<Authority> ls;
		ls = new ArrayList<>();
		Authority authority;
		authority = new Authority();
		authority.setAuthority(Authority.CUSTOMER);
		ls.add(authority);
	
		userAccount.setAuthorities(ls);	
		UserAccount saved = userAccountService.save(userAccount);
		
		Collection<Box> boxes = boxService.createDefault();
		
		result.setUserAccount(saved);
		result.setBoxes(boxes);
		result.setProfiles(profiles);
		result.setReceivedMessages(new LinkedList<Message>());
		result.setSentMessages(new LinkedList<Message>());
		result.setEndorsements(endorserments);
		

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
		
		Assert.isTrue(customer.getEmail().matches("^([A-z0-9 ]{1,}<[A-z0-9]{1,}@[A-z0-9.]{1,}>|[A-z0-9]{1,}@[A-z0-9.]{1,})$")); // I do this here because admins can also have an email without domain (abcdef@) but the rest of users can't
		
		String phoneNumber = customer.getPhoneNumber();
		if(!phoneNumber.startsWith("+")) {
			customer.setPhoneNumber("+34" + phoneNumber);  // TODO: Coger prefijo de systemData
		}

		Customer result;

		result = customerRepository.save(customer);

		return result;
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
