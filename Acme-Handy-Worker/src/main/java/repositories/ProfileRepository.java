package repositories;



import java.util.Collection;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

	@Query("select a.profiles from Actor a where a.id=?1")
	public Collection<Profile> getActorProfiles(int ActorId);
	

}