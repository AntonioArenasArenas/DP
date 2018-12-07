package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import domain.Referee;

public interface RefereeRepository extends JpaRepository<Referee, Integer>{

	@Query("select r from Referee r where r.userAccount.id = ?1")
	Referee findByUserAccountId(int userAccountId);
	
}
