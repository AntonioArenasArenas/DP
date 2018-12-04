package security;

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

@ContextConfiguration(locations = {
		"classpath:spring/junit.xml"
	})
	@RunWith(SpringJUnit4ClassRunner.class)
	@Transactional
public class UserAccountServiceTest extends AbstractTest{
	// Supporting services ----------------------------------------------------
				@Autowired
				private UserAccountService userAccountService;
				
				
				
			// Test ----------------------------------------------------
				
		
		@Test
		public void testCreateUserAccount(){
			super.authenticate("superman");
			Authority au = new Authority();
			au.setAuthority(Authority.CUSTOMER);
			
			UserAccount acc = userAccountService.create();
			acc.getAuthorities().add(au);
			acc.setUsername("username");
			acc.setPassword("password");
			UserAccount saved = userAccountService.save(acc);
			
			Collection<UserAccount> accounts = userAccountService.findAll();
			
			Assert.isTrue(accounts.contains(saved));
			
			
		}
		
		
		
		
}