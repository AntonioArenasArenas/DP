package services;


import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;






import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import domain.Application;
import domain.Category;
import domain.Complaint;
import domain.Phase;

import domain.Task;
import domain.Warranty;


import security.LoginService;
import security.UserAccount;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class TaskServiceTest extends AbstractTest{

	// Supporting services ----------------------------------------------------
				@Autowired
				private TaskService	taskService;
				
				@Autowired
				private ComplaintService complaintService;
				
				@Autowired
				private PhaseService phaseService;
				
				@Autowired
				private WarrantyService warrantyService;
				
				@Autowired
				private CategoryService categoryService;
				
				@Autowired
				private ApplicationService applicationService;
				
				@Autowired
				private CustomerService customerService;
				
				
				@Test
				public void testCreate(){
					
					super.authenticate("superguay");
					
					taskService.createTask();
					
					super.authenticate(null);
					
				}
				
				@Test
				public void testCreateTask(){
					
					Task  saved; System.out.println("1");
					Task task = new Task();System.out.println("2");
					super.authenticate("superguay");System.out.println("3");
//					Collection<Task> tasks = taskService.findAll();
//					LinkedList<Task> listaDeTasks = new LinkedList<Task>(tasks);      //Comprobar si se pudiese hacer un test con esto
//					task = listaDeTasks.getFirst();
				//	tasks = taskService.createTask(listaDeTasks.getFirst());
					task.setTicker(taskService.tickerGenerator());System.out.println("4");
					task.setMoment(new Date (System.currentTimeMillis() - 1));System.out.println("5");
					task.setDescription("Descripcion de prueba");System.out.println("6");
					task.setMaxPrice(2.1);System.out.println("7");
					task.setComments("comentario de prueba");System.out.println("8");
					
					Collection<Complaint> complaints = complaintService.findAll();System.out.println("9");
					task.setComplaints(complaints);System.out.println("10");
					
					Collection<Phase> phases = phaseService.findAll();System.out.println("11");
					task.setPhases(phases);System.out.println("12");
					
					LinkedList<Warranty> warranties = new LinkedList<Warranty>(warrantyService.findAll());System.out.println("13");
					task.setWarranty(warranties.getFirst());System.out.println("14");
					
					LinkedList<Category> categories = new LinkedList<Category>(categoryService.findAll());System.out.println("15");
					task.setCategory(categories.getFirst());System.out.println("16");
					
					Collection<Application> applications = applicationService.findAll();System.out.println("17");
					task.setApplications(applications);System.out.println("18");
					
					saved = taskService.save(task);System.out.println("19");
// Metodo 1			UserAccount userAccount = LoginService.getPrincipal();
// 					Collection<Task> tasks = customerService.findOne(userAccount.getId()).getTasks();
					
// Metodo 2	 		Collection<Task> tasks = customerService.findOne(super.getEntityId("superguay")).getTasks();System.out.println("20");
// Metodo 3			Collection<Task> tasks = taskService.findAll();System.out.println("20");
//					Assert.isTrue(tasks.contains(saved));System.out.println("21");
					super.authenticate("superguay");System.out.println("22");
					
				}
				
				@Test
				public void testDeleteTask(){
					
					super.authenticate("superguay");
					Collection<Task> tasks = taskService.findAll();
					LinkedList<Task> listaDeTasks = new LinkedList<Task>(tasks);
					Task task = listaDeTasks.getFirst();
					Assert.notNull(task);
					Assert.isTrue(taskService.findAll().contains(task));
					taskService.delete(task);
					customerService.actualizarTasks(task);
					Assert.isTrue(!(taskService.findAll().contains(task)));
					super.authenticate(null);
							
				}
				
				@Test
				public void testUpdateTask(){
					
					super.authenticate("superguay");
					UserAccount userAccount = LoginService.getPrincipal();System.out.println("1");
			//1		Collection<Task> tasks = customerService.findOne(super.getEntityId("superguay")).getTasks();
			//2		Collection<Task> tasks = customerService.findOne(userAccount.getId()).getTasks();System.out.println("2");
					Collection<Task> tasks = taskService.findAll();System.out.println("20");
					LinkedList<Task> listaDeTasks = new LinkedList<Task>(tasks);
					Task task = listaDeTasks.getFirst();
					Assert.notNull(task);
				//	Assert.isTrue(customerService.contineneTask(userAccount.getId(), task));  De aqui viene el problema en el delete!!!!!
					taskService.save(task);
					super.authenticate(null);
					
				}
				

				
				
}
