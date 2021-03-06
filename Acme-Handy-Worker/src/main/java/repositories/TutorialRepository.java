package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Tutorial;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Integer>{

	@Query("select w.tutorials from Worker w where w.id=?1")
	public Collection<Tutorial> getTutorialsByWorkerId(int workerId);

}
