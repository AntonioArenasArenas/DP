package services;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TaskRepository;
import domain.Actor;
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
	
	@Autowired
	private ActorService actorService;

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
		
		Assert.isTrue(task.getStartDate().before(task.getEndDate()));
		
		Actor logged = actorService.findByPrincipal();
		if(task.getId() != 0) {
			Assert.isTrue(task.getCustomer() == logged);
		} else {
			task.setMoment(new Date(System.currentTimeMillis() - 1));
		}
		
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
	
	public Collection<Task> getActiveTasks(){
		
		return taskRepository.getActiveTasks();

	}
	
	public Collection<Task> getTasksByLogged(){
		
		Customer customer = customerService.findByPrincipal();
		
		return taskRepository.getTasksByCustomerId(customer.getId());

	}
	
	public Collection<Task> getActiveTasksByCustomerId(Integer customerId) {
		return taskRepository.getActiveTasksByCustomerId(customerId);
	}
	
	public Collection<Task> getTasksWithAcceptedApplicationsByWorkerId(int workerId) {
		return taskRepository.getTasksWithAcceptedApplicationsByWorkerId(workerId);
	}
	
	public Collection<Task> getTasksByWorkerId(int workerId) {
		return taskRepository.getTasksByWorkerId(workerId);
	}
	
	public Double[] tasksPerUserStatistics(){
		
		Double[] res = new Double[4];
		
		Collection<Long> numTasksPerCust = taskRepository.numberOfTasksPerCustomer();
		
		Double sum = 0.0;
		for(Long n : numTasksPerCust) {
			sum += n;
		}
		res[0] = sum.doubleValue()/numTasksPerCust.size(); // Average
		
		res[1] = Collections.min(numTasksPerCust).doubleValue();
		
		res[2] = Collections.max(numTasksPerCust).doubleValue();
		
		Double num = 0.0;
		Double numi = 0.0;
		for(Long n : numTasksPerCust) {
			numi = Math.pow(n - res[0], 2);
			num += numi;
		}
		res[3] = Math.sqrt(num/numTasksPerCust.size()); // Standard deviation
		
		return res;
		
	}

	public String tickerGenerator(){
		String charactersL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		String result2 = "";
		int chars = 6;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String result1 = sdf.format(new Date());

		char[] part2 = new char[chars];

		for (int i = 0 ; i < chars ; i++){
			part2[i] = charactersL.charAt(random.nextInt(charactersL.length()));
		}

		for (int i = 0 ; i < chars ; i++){
			result2 +=part2[i];
		}

		return result1 + "-" + result2;

	}

}
