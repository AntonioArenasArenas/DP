package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.WarrantyRepository;
import domain.Task;
import domain.Warranty;

@Service
@Transactional
public class WarrantyService {

	@Autowired
	private WarrantyRepository warrantyRepository;
	
	@Autowired
	private TaskService taskService;


//Constructors -----------------------------------------------------------

	public WarrantyService() {
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------------

		public Collection<Warranty> findAll() {
			Collection<Warranty> result;

			result = warrantyRepository.findAll();
			Assert.notNull(result);

			return result;
		}

		public Warranty findOne(int warrantyid) {
			Assert.isTrue(warrantyid != 0);

			Warranty result;

			result = warrantyRepository.findOne(warrantyid);
			Assert.notNull(result);
			
			
			return result;
		}

		public Warranty createWarranty() {
			
			Warranty warranty = new Warranty();
			warranty.setDraftMode(true);
			return warranty;
			
		}

		public Warranty save(Warranty warranty) {

			Assert.notNull(warranty);

			Warranty result;

			Assert.isTrue(warranty.getDraftMode()==true);
			
			result = warrantyRepository.save(warranty);

			return result;
		}
		
		public void delete(Warranty warranty){
			
			Assert.notNull(warranty);
			
			Boolean warrantyIsInTask = false;
			for(Task task : taskService.findAll()) {
				if(task.getWarranty().equals(warranty)){
					warrantyIsInTask = true;
					break;
				}
			}
			Assert.isTrue(!warrantyIsInTask);
			
			Assert.isTrue(warranty.getDraftMode()==true);
			
			warrantyRepository.delete(warranty);
		}
		
		
	
	
	
	
}