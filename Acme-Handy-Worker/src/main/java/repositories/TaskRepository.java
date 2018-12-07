package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{

	// Para probarlo con la base de datos esta query y ver que funciona(select c.tasks from Customer c where c.id=426;)
	@Query("select c.tasks from Customer c where c.id=?1")
	public Collection<Task> getTasksByCustomerId(int customerId);
	
}
