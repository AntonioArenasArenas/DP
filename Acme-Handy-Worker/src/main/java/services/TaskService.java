package services;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TaskRepository;
import domain.Customer;
import domain.Task;

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

	public Task create() {

		Task result;

		result = new Task();
		
		Customer customer = customerService.findByPrincipal();
		
		result.setCustomer(customer);

		result.setTicker(tickerGenerator());

		return result;
	}

	public Task save(Task task) {

		Assert.notNull(task);

		Task result;

		task.setMoment(new Date(System.currentTimeMillis() - 1));
		
		result = taskRepository.save(task);
		
		return result;
	}

	public void delete(Task task) {
		Customer c = customerService.findByPrincipal();
		Assert.notNull(task);
		Assert.isTrue(getTasksByCustomerId(c.getId()).contains(task));
		taskRepository.delete(task);

	}

	// Other business methods -------------------------------------------------

	public Collection<Task> getTasksByCustomerId(Integer id){
		
		return taskRepository.getTasksByCustomerId(id);

	}
	
	public Collection<Task> getTasksByLogged(){
		
		Customer customer = customerService.findByPrincipal();
		
		return taskRepository.getTasksByCustomerId(customer.getId());

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

//	public Collection<Task> activeTasks(Collection<Task> tasks){ //Para mostrarle al customer las task activas que puede elegir
//		Collection<Task> result ;
//		result = taskRepository.findAll();
//		Date now = Calendar.getInstance().getTime();
//		for (Task t: result){
//			if(t.getEndDate().after(now)){
//				result.remove(t);
//			}
//		}
//		return result;
//
//	}

}
