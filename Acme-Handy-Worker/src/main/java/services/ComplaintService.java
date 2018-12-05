package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ComplaintRepository;
import domain.Complaint;
import domain.Customer;
import domain.Referee;
import domain.Task;
import domain.Worker;

@Service
@Transactional
public class ComplaintService {

	@Autowired
	private ComplaintRepository complaintRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private WorkerService workerService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private RefereeService refereeService;

	// Constructors -----------------------------------------------------------

	public ComplaintService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Collection<Complaint> findAll() {
		Collection<Complaint> result;

		result = complaintRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Complaint findOne(int complaintId) {
		Assert.isTrue(complaintId != 0);

		Complaint result;

		result = complaintRepository.findOne(complaintId);
		Assert.notNull(result);

		return result;
	}

	public Complaint createComplaint(Task task) {

		Customer customer = customerService.findByPrincipal();
		Assert.isTrue(customer.getTasks().contains(task));

		Complaint result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new Complaint();

		result.setTicker(taskService.tickerGenerator());
		result.setMoment(moment);

		return result;
	}

	public Complaint save(Complaint complaint, Task task) {

		Customer customer = customerService.findByPrincipal();
		Assert.isTrue(customer.getTasks().contains(task));

		Assert.notNull(complaint);
		Complaint result;
		
		result = complaintRepository.save(complaint);
		
		task.getComplaints().add(result);
		
		taskService.save(task);

		return result;
	}

	public void delete(Complaint complaint, Task task) {

		Customer customer = customerService.findByPrincipal();
		Assert.isTrue(customer.getTasks().contains(task));

		Assert.notNull(complaint);
		Assert.isTrue(complaint.getId() != 0);
		Assert.isTrue(complaintRepository.exists(complaint.getId()));

		complaintRepository.delete(complaint);
	}

	// Other business methods -------------------------------------------------
	
	public Collection<Complaint> findCustomerComplaints() {

		Customer c = customerService.findByPrincipal();

		return complaintRepository.getComplaintsByCustomerId(c.getId());
		
	}

	public Collection<Complaint> findWorkerComplaints() {

		Worker worker = workerService.findByPrincipal();

		return complaintRepository.getComplaintsByWorkerId(worker.getId());

	}
	
	public Collection<Complaint> getComplaintsWithNoReferee() {
		
		return complaintRepository.getComplaintsWithNoReferee();
		
	}
	
	// Currently, according to our UML, the only way to assign a referee to a
	// complaint is creating a report. As that is the purpose of the methods
	// createReport and save in the reportService, I didn't create the method
	// to fulfill the requirement 36.1b here.
	
	
	public Collection<Complaint> getSelfAssignedComplaints() {
		
		Referee referee = refereeService.findByPrincipal();
		
		return complaintRepository.getComplaintsByRefereeId(referee.getId());
		
	}
	
	public Double[] getComplaintsPerTaskStatistics() {

		Double[] result = complaintRepository.getComplaintsPerTaskStatistics();

		return result;

	}
	
	public Double ratioOfTasksWithComplaint() {

		Double result = complaintRepository.ratioOfTasksWithComplaint();

		return result;

	}
	
	public List<Customer> topThreeCustomers() {

		List<Customer> result = complaintRepository.customersOrderedByComplaints();
		
		result = result.subList(0, Math.min(result.size(), 3));
		
		return result;

	}
	
	public List<Worker> topThreeWorkers() {

		List<Worker> result = complaintRepository.workersOrderedByComplaints();
		
		result = result.subList(0, Math.min(result.size(), 3));
		
		return result;

	}

}
