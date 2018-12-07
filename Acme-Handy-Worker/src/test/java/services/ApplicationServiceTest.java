package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Application;
import domain.CreditCard;
import domain.Task;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class ApplicationServiceTest extends AbstractTest {

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private TaskService taskService;

	@Test
	public void testFindCustomerApplications() {
		Collection<Application> applications;
		super.authenticate("superfuerte");
		applications = applicationService.findCustomerApplications();
		Assert.isTrue(applications.size() == 2);
		super.authenticate(null);

	}

	@Test
	public void testUpdateApplicationRejected() {

		super.authenticate("superman");
		Application application = new Application();
		Collection<Application> applications = applicationService.findAll();
		Iterator<Application> it = applications.iterator();
		boolean exit = false;
		while (!exit) {
			Application a = it.next();
			if (a.getStatus().equals("PENDING")) {
				application = a;
				exit = true;
			}
		}
		application.setStatus("REJECTED");
		Application saved = applicationService.updateStatus(application, null,
				null);
		Assert.isTrue(saved.getStatus().equals(application.getStatus()));
		super.authenticate(null);
	}

	@Test
	public void testUpdateApplicationAccepted() {

		super.authenticate("superman");
		Application application = new Application();
		Collection<Application> applications = applicationService.findAll();
		Iterator<Application> it = applications.iterator();
		boolean exit = false;
		while (!exit && it.hasNext()) {
			Application a = it.next();
			if (a.getStatus().equals("PENDING")) {
				application = a;
				exit = true;
			}
		}
		Assert.notNull(application);
		application.setStatus("ACCEPTED");
		CreditCard creditcard = new CreditCard();
		creditcard.setHolderName("Fulanito");
		creditcard.setBrandName("Tarjeta Master");
		creditcard.setNumber("3234235233253");
		creditcard.setCvv(101);
		Date date = new Date();
		SimpleDateFormat simpledataformat = new SimpleDateFormat("yyyy-MM");
		try {
			date = simpledataformat.parse("2019-12");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		creditcard.setExpirationDate(date);
		Application saved = applicationService.updateStatus(application,
				creditcard, "Comentarios varios");
		Assert.isTrue(saved.getStatus().equals(application.getStatus()));
		Assert.isTrue(saved.getCreditCard().equals(creditcard));
		Assert.isTrue(saved.getComments().equals("Comentarios varios"));
		super.authenticate(null);
	}

	@Test
	public void testFindWorkerApplications() {

		Collection<Application> applications;
		super.authenticate("admin");
		applications = applicationService.findWorkerApplications();
		Assert.isTrue(applications.size() == 1);
		super.authenticate(null);
	}

	@Test
	public void testCreateApplication() {

		Application application, saved;
		super.authenticate("supertroll");
		Collection<Task> tasks = taskService.findAll();
		LinkedList<Task> taskslist = new LinkedList<Task>(tasks);
		application = applicationService
				.createApplication(taskslist.getFirst());

		saved = applicationService.save(application);
		Collection<Application> applications = applicationService.findAll();
		Assert.isTrue(applications.contains(saved));
		super.authenticate(null);

	}

	@Test
	public void testApplicationPerTaskStatistics() {

		Double[] statistics;
		statistics = applicationService.getAdminStatisticsPerTask();
		Assert.isTrue(statistics[0].equals(0.75));
		Assert.isTrue(statistics[1].equals(0.));
		Assert.isTrue(statistics[2].equals(1.));
		Assert.isTrue(statistics[3].equals(0.433));
	}

	@Test
	public void testApplicationPriceStatistics() {
		Double[] statistics;
		statistics = applicationService.getAdminStatisticsPriceOffered();
		Assert.isTrue(statistics[0].equals(57.77333333333333));
		Assert.isTrue(statistics[1].equals(0.99));
		Assert.isTrue(statistics[2].equals(151.23));
		Assert.isTrue(statistics[3].equals(66.59186353368472));
	}

	@Test
	public void testApplicationCantChange() {

		Double result;
		result = applicationService.getAppliCantChange();
		Assert.isTrue(result.equals(0.333333));
	}

	@Test
	public void testApplicationMaximumPriceStatistics() {

		Double[] result;
		result = applicationService.getAdminStatisticsMaximumPrice();
		Assert.isTrue(result[0].equals(355.3633333333333));
		Assert.isTrue(result[1].equals(153.26));
		Assert.isTrue(result[2].equals(456.58));
		Assert.isTrue(result[3].equals(142.90870100250098));
	}

	@Test
	public void testPendingApplications() {

		Double result;
		result = applicationService.getPendApplications();
		Assert.isTrue(result.equals(0.333333));
	}

	@Test
	public void testAcceptedApplications() {

		Double result;
		result = applicationService.getAcepApplications();
		Assert.isTrue(result.equals(0.333333));
	}

	@Test
	public void testRejectedApplications() {

		Double result;
		result = applicationService.getRejecApplications();
		Assert.isTrue(result.equals(0.333333));
	}
}
