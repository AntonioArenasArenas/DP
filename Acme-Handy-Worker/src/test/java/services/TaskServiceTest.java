package services;

import java.util.Collection;
import java.util.Date;
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
import domain.Application;
import domain.Category;
import domain.Complaint;
import domain.Customer;
import domain.Phase;
import domain.Task;
import domain.Warranty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class TaskServiceTest extends AbstractTest {

	@Autowired
	private TaskService taskService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private WarrantyService warrantyService;

	@Autowired
	private CustomerService customerService;

	@Test
	public void testCreateTask() {

		Task task, saved;
		super.authenticate("superguay");
		task = taskService.createTask();
		task.setStartDate(new Date(System.currentTimeMillis() + 360000));
		task.setEndDate(new Date(System.currentTimeMillis() + 7200000));
		task.setDescription("Hola");
		task.setMaxPrice(288.);
		task.setComments("Comment sfkjshodgjf");
		Collection<Category> category = categoryService.findAll();
		LinkedList<Category> categorieslist = new LinkedList<Category>(category);
		task.setCategory(categorieslist.getFirst());
		Collection<Warranty> warranty = warrantyService.findAll();
		LinkedList<Warranty> warrantieslist = new LinkedList<Warranty>(warranty);
		task.setWarranty(warrantieslist.getFirst());
		task.setComplaints(new HashSet<Complaint>());
		task.setApplications(new HashSet<Application>());
		task.setPhases(new HashSet<Phase>());
		Collection<Customer> customer = customerService.findAll();
		LinkedList<Customer> customerslist = new LinkedList<Customer>(customer);
		task.setCustomer(customerslist.getFirst());
		saved = taskService.save(task);
		Collection<Task> tasks = taskService.findAll();
		Assert.isTrue(tasks.contains(saved));
		super.authenticate(null);

	}

	@Test
	public void testDeleteTask(){

		super.authenticate("superguay");
		Collection<Task> tasks = taskService.getTasksByCustomerId(customerService.findByPrincipal().getId());
		LinkedList<Task> taskList = new LinkedList<Task>(tasks);
		Task task = taskList.getFirst();
		Assert.notNull(task);
		taskService.delete(task);
		Assert.isTrue(!(taskService.findAll().contains(task)));
		super.authenticate(null);

	}
	
	@Test
	public void testFindAll() {
		Collection<Task> tasks = taskService.findAll();

		Assert.isTrue(tasks.size() == 3);
	}


}
