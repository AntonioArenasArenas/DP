package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdminRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Admin;
import domain.Box;
import domain.Message;
import domain.Profile;



@Service
@Transactional
public class AdminService {

	
	@Autowired
	private AdminRepository	adminRepository;
	

	// Supporting services ----------------------------------------------------
	@Autowired
	private BoxService boxService;
	
	@Autowired
	private UserAccountService userAccountService;
	
	
	
	//CRU
	
	public Admin create() {
		Admin result;
		result = new Admin();
		UserAccount userAccount;
		userAccount = new UserAccount();
		Collection<Profile> profiles = new ArrayList<Profile>();
		Collection<Box> box;
		box = boxService.createDefault();
		
		
		List<Authority> ls;
		ls = new ArrayList<>();
		Authority authority;
		authority = new Authority();
		authority.setAuthority(Authority.ADMIN);
		ls.add(authority);
		
		userAccount.setAuthorities(ls);	

		result.setUserAccount(userAccount);
		result.setBoxes(box);
		result.setProfiles(profiles);
		result.setReceivedMessages(new LinkedList<Message>());
		result.setSentMessages(new LinkedList<Message>());
		

		return result;
	}
	
	public Admin save(Admin Admin) {
		Assert.notNull(Admin);
		
		

		Admin result;
		
		result = adminRepository.save(Admin);
		

		return result;
	}
	
	public Admin findOne(int adminId) {
		Admin result;

		result = adminRepository.findOne(adminId);
		Assert.notNull(result);

		return result;
	}
	
	public Admin findByPrincipal() {
		
		Admin result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}
	
	public Admin findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);

		Admin result;

		result = adminRepository.findByUserAccountId(userAccount.getId());

		return result;
	}
	
}


