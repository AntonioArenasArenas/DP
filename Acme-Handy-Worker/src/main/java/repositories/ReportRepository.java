package repositories;

import java.util.Collection;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer>{
	
	@Query("select rep from Report rep where rep.complaint.referee.id=?1")
	public Collection<Report> getReportsByRefereeId(int refereeId);
	
	@Query("select comp.report from Task t join t.complaints comp where final_report = true and t.customer.id=?1")
	public Collection<Report> getReportsByCustomerId(int customerId);
	
}
