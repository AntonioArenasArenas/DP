package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdminRepository;
import security.LoginService;
import security.UserAccount;
import domain.Admin;


@Service
@Transactional
public class AdminService {

	
	@Autowired
	private AdminRepository	adminRepository;
	
	
	
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


