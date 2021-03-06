package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;
import domain.Worker;

@Repository
public interface ApplicationRepository extends
		JpaRepository<Application, Integer> {

	@Query("select w.applications from Worker w where w.id=?1")
	public Collection<Application> getApplicationsByWorkerId(int WorkerId);

	@Query("select t.applications from Task t where t.customer.id=?1")
	public Collection<Application> getApplicationsByCustomerId(int CustomerId);

	@Query("select avg(t.applications.size), min(t.applications.size), max(t.applications.size), stddev(t.applications.size) from Task t")
	public Double[] getApplicationPerTaskStatistics();

	@Query("select avg(a.offeredPrize),min(a.offeredPrize), max(a.offeredPrize), stddev(a.offeredPrize) from Application a")
	public Double[] getApplicationPriceStatistics();

	@Query("select count(a)*1.00 / (select count(a) from Application a where a.status='PENDING') from Application a where a.status='PENDING' AND a.task.endDate<CURRENT_DATE")
	public Double getApplicationCantChange();

	@Query("select w from Worker w join w.applications a where a.status='ACCEPTED' group by w having  w.applications.size >= ( select avg(w1.applications.size) from Worker w1) order by w.applications.size")
	public Collection<Worker> getWorkersAcceptedMAvg();

	@Query("select count(a)*1.00 / (select count(a) from Application a) from Application a where a.status='PENDING'")
	public Double getPendingApplications();

	@Query("select count(a)*1.00 / (select count(a) from Application a) from Application a where a.status='ACCEPTED'")
	public Double getAcceptedApplications();

	@Query("select count(a)*1.00 / (select count(a) from Application a) from Application a where a.status='REJECTED'")
	public Double getRejectedApplications();

	@Query("select a from Worker w join w.applications a where w.id=?1 AND a.task.id=?2 AND a.status='ACCEPTED'")
	public Collection<Application> getWorkerAcceptedApplicationsByTaskId(int workerId, int taskId);

}
