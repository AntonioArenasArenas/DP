package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Profile;
import domain.Report;


import repositories.ProfileRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class ProfileService {
	
	@Autowired
	private ProfileRepository profileRepository;
	
	//supported Services
	@Autowired
	private ActorService actorService;
	
	public Collection<Profile> findAll() {
		Collection<Profile> result;

		result = profileRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Profile findOne(int profileId) {
		Assert.isTrue(profileId != 0);

		Profile result;

		result = profileRepository.findOne(profileId);
		Assert.notNull(result);

		return result;
	}
	
	public Collection<Profile> findProfiles() {
		Collection<Profile> result;
		
		UserAccount userAccount = LoginService.getPrincipal();
		Actor logged = actorService.findByUserAccount(userAccount);
		
		result = profileRepository.getActorProfiles(logged.getId());
		Assert.notNull(result);

		return result;
	}
	
	public Profile create(){
		
		Profile result = new Profile();
		return result;
	}
	
	public Profile save(Profile profile){
		Assert.notNull(profile);
		
		Profile result = profileRepository.save(profile);
		
		if(profile.getId()==0){
			Actor actor = actorService.findByPrincipal();
			Collection<Profile> profiles = actor.getProfiles();
			profiles.add(result) ;
			actor.setProfiles(profiles);
			
		}
		
		return result;
	}
	
	public void delete(Profile profile) {

		Actor actor = actorService.findByPrincipal();

		Assert.notNull(profile);
		Assert.isTrue(profile.getId() != 0);
		Assert.isTrue(actor.getProfiles().contains(profile));
		actor.getProfiles().remove(profile);
		actorService.save(actor);
	
		profileRepository.delete(profile);
		
	}

}
