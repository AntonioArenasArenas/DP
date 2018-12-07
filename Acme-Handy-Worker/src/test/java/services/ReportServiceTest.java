package services;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Complaint;
import domain.Note;
import domain.Report;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class ReportServiceTest extends AbstractTest {

	@Autowired
	private ReportService reportService;

	@Autowired
	private ComplaintService complaintService;

	@Test
	public void testFindCustomerReports() {
		Collection<Report> reports;
		super.authenticate("superfuerte");
		reports = reportService.findCustomerReports();
		Assert.isTrue(reports.size() == 1);
		super.authenticate(null);

	}
	
	@Test
	public void testFindRefereeReports() {
		Collection<Report> reports;
		super.authenticate("brawls");
		reports = reportService.findRefereeReports();
		Assert.isTrue(reports.size() == 1);
		super.authenticate(null);

	}

	@Test
	public void testCreateReport() {

		Report report, saved;
		super.authenticate("TEGOCABEZON");
		Collection<Complaint> complaints = complaintService.findAll();
		LinkedList<Complaint> complaintslist = new LinkedList<Complaint>(complaints);
		report = reportService
				.createReport(complaintslist.getFirst());
		
		report.setDescription("Descripción de report de prueba");
		report.setFinalReport(false);
		Collection<Note> notes = new HashSet<Note>();
		report.setNotes(notes);
		Collection<String> attachments = new HashSet<String>();
		report.setAttachments(attachments);

		saved = reportService.save(report);
		Collection<Report> reports = reportService.findAll();
		Assert.isTrue(reports.contains(saved));
		super.authenticate(null);

	}
}
