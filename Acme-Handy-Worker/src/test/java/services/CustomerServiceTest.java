package services;

import java.util.Collection;
import java.util.LinkedList;

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
import domain.Customer;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CustomerServiceTest extends AbstractTest {
	// Supporting services ----------------------------------------------------
	@Autowired
	private CustomerService customerService;
	@Autowired
	private UserAccountService userAccountService;

	// Test ----------------------------------------------------

	@Test
	public void testCreateCustomer() {

		Customer customer = customerService.create();
		UserAccount acc = userAccountService.create();
		Authority au = new Authority();
		au.setAuthority(Authority.CUSTOMER);

		acc.getAuthorities().add(au);
		acc.setPassword("password");
		acc.setUsername("username");

		UserAccount acc_saved = userAccountService.save(acc);
		customer.setUserAccount(acc_saved);

		Customer saved = customerService.save(customer);

		Assert.notNull(saved);
		Assert.isTrue(saved.getBoxes().size() == 4);

	}

	@Test
	public void testCreate() {
		final Customer c = this.customerService.create();
		Assert.notNull(c);
		final Authority auth = (Authority) c.getUserAccount().getAuthorities()
				.toArray()[0];
		Assert.isTrue(auth.getAuthority().equals("CUSTOMER"));

	}

	@Test
	public void testDelete() {

		Collection<Customer> customers = customerService.findAll();
		LinkedList<Customer> customerslist = new LinkedList<Customer>(customers);
		Customer c = customerslist.getFirst();

		customerService.delete(c);

		Collection<Customer> customerssaved = customerService.findAll();

		Assert.isTrue(!customerssaved.contains(c));
	}

	@Test
	public void testFindAll() {
		Collection<Customer> customers = customerService.findAll();

		Assert.isTrue(customers.size() == 4);
	}

}
