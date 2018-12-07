package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import domain.Application;
import domain.CreditCard;
import domain.Customer;
import domain.Task;
import domain.Worker;

@Service
@Transactional
public class ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private WorkerService workerService;

	@Autowired
	private CustomerService customerService;

	// Constructors -----------------------------------------------------------

	public ApplicationService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Collection<Application> findAll() {
		Collection<Application> result;

		result = applicationRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Application findOne(int applicationId) {
		Assert.isTrue(applicationId != 0);

		Application result;

		result = applicationRepository.findOne(applicationId);
		Assert.notNull(result);
		
		
		return result;
	}

	public Application createApplication(Task task) {

		Worker worker = workerService.findByPrincipal();

		Application result;

		result = new Application();

		result.setWorker(worker);
		result.setStatus("PENDING");
		result.setTask(task);

		return result;
	}

	public Application save(Application application) {

		Worker worker = workerService.findByPrincipal();

		Assert.notNull(application);

		Assert.isTrue(application.getWorker().equals(worker));

		Application result;

		application.setMoment(new Date(System.currentTimeMillis() - 1));
		result = applicationRepository.save(application);

		return result;
	}

	// Other business methods -------------------------------------------------
	public Collection<Application> findWorkerApplications() {

		Worker worker = workerService.findByPrincipal();

		return applicationRepository.getApplicationsByWorkerId(worker.getId());

	}

	public Collection<Application> findCustomerApplications() {

		Customer c = customerService.findByPrincipal();

		return applicationRepository.getApplicationsByCustomerId(c.getId());
	}

	public Application updateStatus(Application application,
			CreditCard creditcard, String comments) {
		Application result;
		Task t = application.getTask();
		Customer c = customerService.findByPrincipal();

		// Ver los tasks que devuelve el findByPrincipal, puede que no devuelva
		// todas o devuelva null, si es así, hacer un método específico que
		// devuelva las task de un customer

		// Dependiendo de la implementación se puede hacer con Ajax el
		// updateStatus o no

		Assert.isTrue(c.getTasks().contains(t));

		if (application.getStatus().equals("ACCEPTED")) {
			Assert.notNull(creditcard);
			application.setCreditCard(creditcard);
		}

		if (comments != null) {
			application.setComments(comments);
		}

		result = applicationRepository.save(application);

		return result;
	}

	public Double[] getAdminStatisticsPerTask() {

		Double[] result = applicationRepository
				.getApplicationPerTaskStatistics();

		return result;

	}

	public Double[] getAdminStatisticsPriceOffered() {

		Double[] result = applicationRepository.getApplicationPriceStatistics();

		return result;

	}

	public Double getAppliCantChange() {

		Double result = applicationRepository.getApplicationCantChange();
		return result;

	}

	public Double[] getAdminStatisticsMaximumPrice() {

		Double[] result = applicationRepository
				.getApplicationMaximuxPriceStatistics();
		return result;
	}

	public Double getPendApplications() {

		Double result = applicationRepository.getPendingApplications();
		return result;
	}

	public Double getAcepApplications() {

		Double result = applicationRepository.getAcceptedApplications();
		return result;
	}

	public Double getRejecApplications() {

		Double result = applicationRepository.getRejectedApplications();
		return result;
	}
	
	
	

}
