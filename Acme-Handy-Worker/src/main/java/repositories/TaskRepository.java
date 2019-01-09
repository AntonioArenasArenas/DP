package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{

	@Query("select t from Task t where t.customer.id=?1")
	public Collection<Task> getTasksByCustomerId(int customerId);
	
	@Query("select t from Task t where t.endDate>CURRENT_DATE")
	public Collection<Task> getActiveTasks();
	
//	@Query("select avg(c.tasks.size), min(c.tasks.size), max(c.tasks.size), stddev(c.tasks.size) from Customer c")
//	public Double[] tasksPerUserStatistics();
	
}
