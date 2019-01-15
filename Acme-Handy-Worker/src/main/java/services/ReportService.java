package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ReportRepository;
import security.LoginService;
import security.UserAccount;
import domain.Complaint;
import domain.Note;
import domain.Referee;
import domain.Report;
import domain.Customer;

@Service
@Transactional
public class ReportService {

	@Autowired
	private ReportRepository reportRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private RefereeService refereeService;
	
	@Autowired
	private CustomerService customerService;
	

	// Constructors -----------------------------------------------------------

	public ReportService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Collection<Report> findAll() {
		Collection<Report> result;

		result = reportRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Report findOne(int reportId) {
		Assert.isTrue(reportId != 0);

		Report result;

		result = reportRepository.findOne(reportId);
		Assert.notNull(result);

		return result;
	}

	public Report createReport(Complaint complaint) {
		 Referee referee = refereeService.findByPrincipal();

		Report result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new Report();
		
		result.setNotes(new HashSet<Note>());
		result.setMoment(moment);
		result.setFinalReport(false);
		result.setComplaint(complaint);
		result.getComplaint().setReferee(referee);

		return result;
	}

	public Report save(Report report) {
		Report result = new Report();
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		if (report.getComplaint().getReferee().getUserAccount().equals(userAccount) && report.isFinalReport() == false){
			result = reportRepository.save(report);
		}
		Assert.notNull(report);

		return result;
	}

	public void delete(Report report) {

		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.isTrue(report.getComplaint().getReferee().getUserAccount()
				.equals(userAccount));

		Assert.notNull(report);
		Assert.isTrue(report.getId() != 0);
		Assert.isTrue(reportRepository.exists(report.getId()));
		
		Assert.isTrue(report.isFinalReport() == false);

		reportRepository.delete(report);
	}

	// Other business methods -------------------------------------------------
	
	public Collection<Report> findRefereeReports() {

		Referee referee = refereeService.findByPrincipal();

		return reportRepository.getReportsByRefereeId(referee.getId());

	}
	
	public Collection<Report> findCustomerReports() {

		Customer c = customerService.findByPrincipal();

		return reportRepository.getReportsByCustomerId(c.getId());
	}

}
