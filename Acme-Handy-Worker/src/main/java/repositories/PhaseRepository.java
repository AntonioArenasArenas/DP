package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Phase;
import domain.Task;

@Repository
public interface PhaseRepository extends JpaRepository<Phase, Integer>{
	
	@Query("select t from Task t join t.phases p where p.id=?1")
	public Task getTaskByPhaseId(int phaseId);

}
