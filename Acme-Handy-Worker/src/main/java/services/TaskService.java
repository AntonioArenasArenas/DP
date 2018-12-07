package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import domain.Customer;

import domain.Task;

import repositories.TaskRepository;
import security.LoginService;
import security.UserAccount;




@Service
@Transactional
public class TaskService {

	
	// Managed repository -----------------------------------------------------

		@Autowired
		private TaskRepository		taskRepository;


		// Supporting services ----------------------------------------------------

	
		
		@Autowired
		private CustomerService	customerService;


		// Constructors -----------------------------------------------------------

		public TaskService() {
			super();
		}

		// Simple CRUD methods ----------------------------------------------------

		public Collection<Task> findAll() {
			Collection<Task> result;
			
			result = taskRepository.findAll();
			Assert.notNull(result);
			
			return result;
		}
		
		public Task findOne(int taskId) {
			
			Assert.isTrue(taskId != 0);

			Task result;

			result = taskRepository.findOne(taskId);
			Assert.notNull(result);

			return result;
		}
		
		public Task createTask(){
			
			Customer customer = customerService.findByPrincipal();
			Task result = new Task(); 
			result.setMoment(new Date (System.currentTimeMillis() - 1));
			result.setTicker(this.tickerGenerator());
			System.out.println("TaskService 1");
			Assert.notNull(result);
			System.out.println("TaskService 2");
			customer.getTasks().add(result);
			System.out.println("TaskService 3");
			return result;
			
			
		}
		
	public Task save(Task task) {
			
			Customer customer = customerService.findByPrincipal();

			
			Assert.notNull(task);
			Task result;
			
			task.setMoment(new Date (System.currentTimeMillis() - 1));
			
			result = taskRepository.save(task);
			
			customer.getTasks().add(result);
			Assert.isTrue(customer.getTasks().contains(result));

			return result;
		}

		
		
		
	

		public void delete(Task task) {


			Customer c= customerService.findByPrincipal();
			Assert.notNull(task);
			Assert.isTrue(c.getTasks().contains(task));	
			Assert.isTrue(taskRepository.exists(task.getId()));		 			
			customerService.actualizarTasks(task);
			taskRepository.delete(task);
		}
		


		
		//Other business methods............
		

		
		public Customer navigateProfile(Integer id){
			UserAccount userAccount = LoginService.getPrincipal();		
			Customer c = customerService.findOne(id);
			Assert.isTrue(c.getId()==userAccount.getId());
			Assert.isTrue(id!=0);
			return customerService.findOne(id);
			
		}
		
		public Collection<Task> listTasksCustomer(Integer id){
				
			
			
			return navigateProfile(id).getTasks();
			
		}
		
		public String tickerGenerator(){
			String charactersL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			String charactersN = "1234567890";
			Random random = new Random();
			String result1 = "";
			String result2 = "";
			int chars = 6;
			
			char[] part1 = new char[chars];
			char[] part2 = new char[chars];
			
			for (int i = 0 ; i < chars ; i++){
				part1[i] = charactersN.charAt(random.nextInt(charactersN.length()));
			}
			
			for (int i = 0 ; i < chars ; i++){
				result1 +=part1[i];
			}
			
			for (int i = 0 ; i < chars ; i++){
				part2[i] = charactersL.charAt(random.nextInt(charactersL.length()));
			}
			
			for (int i = 0 ; i < chars ; i++){
				result2 +=part2[i];
			}
			
			return result1 + "-" + result2;
			
		}
		
		public Collection<Task> tasksActivas(Collection<Task> tasks){ //Para mostrarle al customer las task activas que puede elegir
			Collection<Task> result ;
			result = taskRepository.findAll();
			for (Task t: result){
				Date now = new Date();
				now = Calendar.getInstance().getTime();
				if(t.getEndDate().after(now)){
					result.remove(t);
				}
			}
			return result;
			
		}
		

		
		
		
}
