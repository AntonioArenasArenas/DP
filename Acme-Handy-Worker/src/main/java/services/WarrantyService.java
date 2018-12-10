package services;

import java.util.Collection;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import domain.Admin;
import domain.Task;
import domain.Warranty;


import repositories.WarrantyRepository;




@Service
@Transactional
public class WarrantyService {

	@Autowired
	private WarrantyRepository warrantyRepository;
	
	@Autowired
	private TaskService taskService;

//	
//	@Autowired
//	private AdminService adminService;


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
			
		//	Admin admin = adminService.findByPrincipal(); Si el contro de quien crea una warranty lo lleva el controller no hace falta aqui?
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