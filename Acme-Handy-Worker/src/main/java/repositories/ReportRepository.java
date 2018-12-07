package repositories;

import java.util.Collection;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer>{
	
//	@Query("select r.Reports from Referee r where r.id=?1")
	@Query("select rep from Report rep where rep.referee.id=?1")
	public Collection<Report> getReportsByRefereeId(int refereeId);
	
	@Query("select comp.report from Customer c join c.tasks t join t.complaints comp where final_report = true and c.id=?1")
	public Collection<Report> getReportsByCustomerId(int customerId);
	
}
