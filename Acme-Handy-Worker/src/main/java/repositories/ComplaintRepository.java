package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Complaint;
import domain.Customer;
import domain.Worker;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer>{
	
	@Query("select t.complaints from Customer c join c.tasks t where c.id=?1")
	public Collection<Complaint> getComplaintsByCustomerId(int customerId);
	
	@Query("select a.task.complaints from Worker w join w.applications a where w.id=?1")
	public Collection<Complaint> getComplaintsByWorkerId(int workerId);
	
	@Query("select c from Complaint c where c.report is empty") // If the complaint doesn't have a report then it doesn't have a referee
	public Collection<Complaint> getComplaintsWithNoReferee();
	
//	@Query("select rep.complaint from Referee ref join ref.reports rep where ref.id=?1")
//	public Collection<Complaint> getComplaintsByRefereeId(int refereeId);
	
	@Query("select avg(t.complaints.size), min(t.complaints.size), max(t.complaints.size), stddev(t.complaints.size) from Task t")
	public Double[] getComplaintsPerTaskStatistics();
	
	@Query("select count(t)*1.00 / ( select count(t) from Task t ) from Task t where t.complaints.size=1")
	public Double ratioOfTasksWithComplaint();
	
	@Query("select c from Customer c join c.tasks t order by t.complaints.size")
	public List<Customer> customersOrderedByComplaints();
	
	@Query("select w from Worker w join w.applications a join a.task t where a.status='ACCEPTED' order by t.complaints.size")
	public List<Worker> workersOrderedByComplaints();

}
