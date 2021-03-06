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
	
	@Query("select t from Task t where t.endDate>CURRENT_DATE and t.customer.id=?1")
	public Collection<Task> getActiveTasksByCustomerId(int customerId);
	
	@Query("select a.task from Worker w join w.applications a where w.id=?1 AND a.status='ACCEPTED'")
	public Collection<Task> getTasksWithAcceptedApplicationsByWorkerId(int workerId);
	
	@Query("select a.task from Worker w join w.applications a where w.id=?1")
	public Collection<Task> getTasksByWorkerId(int workerId);
	
	@Query("select count(t) from Task t group by t.customer")
	public Collection<Long> numberOfTasksPerCustomer();
	
	@Query("select avg(t.maxPrice), min(t.maxPrice), max(t.maxPrice), stddev(t.maxPrice) from Task t")
	public Double[] maxPricePerTaskStatistics();
	
}
