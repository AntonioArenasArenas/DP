package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Warranty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class WarrantyServiceTest extends AbstractTest{

	
	@Autowired
	private WarrantyService warrantyService;
	
	@Test
	public void testCreateWarranty() {

		Warranty warranty, saved;
		warranty = warrantyService.createWarranty();
		warranty.setTitle("Título 1");
		warranty.setTerms("Términos y condiciones del servicio");
		warranty.setLaws("Leyes leyes leyes");
		saved = warrantyService.save(warranty);
		Collection<Warranty> warranties = warrantyService.findAll();
		Assert.isTrue(warranties.contains(saved));

	}
	
	@Test
	public void testDeleteWarranty(){
//		Collection<Warranty> warranties = warrantyService.findAll();
//		LinkedList<Warranty> listaWarranties = new LinkedList<Warranty>(warranties);
//		Warranty warranty = listaWarranties.getFirst();
		Warranty warranty = warrantyService.findOne(439); // This is a warranty with no task
		Assert.notNull(warranty);
		Assert.isTrue(warranty.getDraftMode()==true);
		warrantyService.delete(warranty);
		Assert.isTrue(!(warrantyService.findAll().contains(warranty)));
		
	}

}
