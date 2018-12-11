package repositories;

import java.util.Collection;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer>{
	
	@Query("select t.applications from Task t where t.customer.id=?1")
	public Collection<Application> getApplicationsByCustomerId(int customerId);

	@Query("select avg(t.applications.size), min(t.applications.size), max(t.applications.size), stddev(t.applications.size) from Task t")
	public Double[] getApplicationPerTaskStatistics();
	
	@Query("select avg(a.offeredPrize),min(a.offeredPrize), max(a.offeredPrize), stddev(a.offeredPrize) from Application a")
	public Double[] getApplicationPriceStatistics();
	
	@Query("select count(a)*1.00 / (select count(a) from Application a) from Application a where a.status='PENDING' AND a.task.endDate<CURRENT_DATE")
	public Double getApplicationCantChange();
	
	@Query("select avg(t.maxPrice), min(t.maxPrice), max(t.maxPrice), stddev(t.maxPrice) from Task t")
	public Double[] getApplicationMaximumPriceStatistics();
	
	@Query("select count(a)*1.00 / (select count(a) from Application a) from Application a where a.status='PENDING'")
	public Double getPendingApplications();
	
	@Query("select count(a)*1.00 / (select count(a) from Application a) from Application a where a.status='ACCEPTED'")
	public Double getAcceptedApplications();
	
	@Query("select count(a)*1.00 / (select count(a) from Application a) from Application a where a.status='REJECTED'")
	public Double getRejectedApplications();
	
}
