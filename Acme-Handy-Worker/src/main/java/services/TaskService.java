package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TaskRepository;
import domain.Task;
import domain.Customer;

@Service
@Transactional
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private CustomerService customerService;

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

	public Task createTask() {

		Task result;

		result = new Task();

		result.setTicker("288288-1ROTO2");

		return result;
	}

	public Task save(Task task) {
		
		Customer customer = customerService.findByPrincipal();

		Assert.notNull(task);

		Task result;

		task.setMoment(new Date(System.currentTimeMillis() - 1));
		result = taskRepository.save(task);
		
		customer.getTasks().add(result);
		customerService.save(customer);

		return result;
	}
	
	public void delete(Task task) {
		Customer c = customerService.findByPrincipal();
		Assert.notNull(task);
		Assert.isTrue(c.getTasks().contains(task));
		c.getTasks().remove(task);
		customerService.save(c);
		taskRepository.delete(task);
		
	}

	// Other business methods -------------------------------------------------
	
	public Collection<Task> listTasksCustomer(Integer id){
			
		Customer c = customerService.findByPrincipal();
		
		return c.getTasks();
		
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
	
	public Collection<Task> activeTasks(Collection<Task> tasks){ //Para mostrarle al customer las task activas que puede elegir
		Collection<Task> result ;
		result = taskRepository.findAll();
		Date now = Calendar.getInstance().getTime();
		for (Task t: result){
			if(t.getEndDate().after(now)){
				result.remove(t);
			}
		}
		return result;
		
	}
	
//	public void updateTasks(Task task){
//		
//		Customer c = customerService.findByPrincipal();
//		
//		if(c.getTasks().contains(task)) {
//			c.getTasks().remove(task);
//		} else {
//			c.getTasks().add(task);
//		}
//		
//		customerService.save(c);
//		
//	}
	
//	public Boolean containsTask( Integer id, Task task){ //Método de utilidad que comprueba si dado una id de customer y una task, dicho customer tiene esa task
//		Boolean r = false;
//		if(customerService.findOne(id).getTasks().contains(task)){
//			r = true;
//		}
//		
//		return r;
//	}

}
