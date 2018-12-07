package repositories;

import java.util.Collection;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer>{
	
	@Query("select w.applications from Worker w where w.id=?1")
	public Collection<Application> getApplicationsByWorkerId(int WorkerId);
	
	@Query("select t.applications from Customer c join c.tasks t where c.id=?1")
	public Collection<Application> getApplicationsByCustomerId(int CustomerId);
	
	@Query("select avg(c.tasks.size),min(c.tasks.size),max(c.tasks.size), stddev(c.tasks.size) from    Customer  c ")
	public Double[] getApplicationPerTaskStatistics();
	
	@Query("select avg(a.offeredPrize),min(a.offeredPrize), max(a.offeredPrize), stddev(a.offeredPrize)   from  Application  a")
	public Double[] getApplicationPriceStatistics();
	
	@Query("select  count(a)*1.00  /  (  select    count(a)  from  Application  a)from  Application  a where a.status='PENDING'AND    a.task.endDate<CURRENT_DATE  ")
	public Double getApplicationCantChange();
	
	@Query("select avg(t.maxPrice),min(t.maxPrice),max(t.maxPrice), stddev(t.maxPrice)     from  Task  t")
	public Double[] getApplicationMaximuxPriceStatistics();
	
	@Query("select  count(a)*1.00  /  (  select  count(a)  from  Application  a)  from  Application  a where a.status='PENDING'")
	public Double getPendingApplications();
	
	@Query("select  count(a)*1.00  /  (  select  count(a)  from  Application  a)  from  Application  a where a.status='ACCEPTED'")
	public Double getAcceptedApplications();
	
	@Query("select  count(a)*1.00  /  (  select  count(a)  from  Application  a)  from  Application  a where a.status='REJECTED'")
	public Double getRejectedApplications();
	
	

}
