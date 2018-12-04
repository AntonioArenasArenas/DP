package services;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Complaint;
import domain.Customer;
import domain.Task;
import domain.Worker;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class ComplaintServiceTest extends AbstractTest {

	@Autowired
	private ComplaintService complaintService;
	
	@Autowired
	private TaskService taskService;
	
	@Test
	public void testFindCustomerComplaints() {
		Collection<Complaint> complaints;
		super.authenticate("superfuerte");
		complaints = complaintService.findCustomerComplaints();
		Assert.isTrue(complaints.size() == 2);
		super.authenticate(null);

	}
	
	@Test
	public void testFindWorkerComplaints() {

		Collection<Complaint> complaints;
		super.authenticate("admin");
		complaints = complaintService.findWorkerComplaints();
		Assert.isTrue(complaints.size() == 0);
		super.authenticate(null);
		
	}

	@Test
	public void testGetComplaintsWithNoReferee() {

		Collection<Complaint> complaints;
		complaints = complaintService.getComplaintsWithNoReferee();
		Assert.isTrue(complaints.size() == 1);
		
	}
	
	@Test
	public void testGetSelfAssignedComplaints() {

		super.authenticate("brawls");
		Collection<Complaint> complaints = complaintService.getSelfAssignedComplaints();
		Assert.isTrue(complaints.size() == 1);
		super.authenticate(null);
	}
	
	@Test
	public void testGetComplaintsPerTaskStatistics() {

		Double[] statistics;
		statistics = complaintService.getComplaintsPerTaskStatistics();
		Assert.isTrue(statistics[0].equals(1.));
		Assert.isTrue(statistics[1].equals(0.));
		Assert.isTrue(statistics[2].equals(2.));
		Assert.isTrue(statistics[3].equals(0.8165));
	}
	
	@Test
	public void testRatioOfTasksWithComplaint() {

		Double ratio;
		ratio = complaintService.ratioOfTasksWithComplaint();
		Assert.isTrue(ratio.equals(0.333333));
	}
	
	@Test
	public void testTopThreeCustomers() {

		List<Customer> complaints = complaintService.topThreeCustomers();
		Assert.isTrue(complaints.size() == 3);

	}
	
	@Test
	public void testTopThreeWorkers() {

		List<Worker> complaints = complaintService.topThreeWorkers();
		Assert.isTrue(complaints.size() == 1);

	}
	
	@Test
	public void testCreateComplaint() {

		Complaint complaint, saved;
		super.authenticate("superguay");
		Collection<Task> tasks = taskService.findAll();
		LinkedList<Task> taskslist = new LinkedList<Task>(tasks);
		Task task = taskslist.getFirst();
		complaint = complaintService.createComplaint(task);
		
		complaint.setDescription("Descripción de complaint de prueba");
		Collection<String> attachments = new HashSet<String>();
		complaint.setAttachments(attachments);

		saved = complaintService.save(complaint, task);
		Collection<Complaint> reports = complaintService.findAll();
		Assert.isTrue(reports.contains(saved));
		super.authenticate(null);

	}
	
}
