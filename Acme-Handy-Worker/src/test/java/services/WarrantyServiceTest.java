package services;

import java.util.Collection;
import java.util.LinkedList;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Warranty;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class WarrantyServiceTest extends AbstractTest{

	
	@Autowired
	private WarrantyService warrantyService;
	
	@Test
	public void testCreateWarranty(){
		
		Collection<Warranty> warranties = warrantyService.findAll();
		LinkedList<Warranty> listaWarranties = new LinkedList<Warranty>(warranties);
		Warranty warranty = listaWarranties.getFirst();
		Assert.notNull(warranty);
		Assert.isTrue(warranty.getDraftMode()==true);
		warrantyService.save(warranty);
	}
	
	@Test
	public void testDeleteWarranty(){
		Collection<Warranty> warranties = warrantyService.findAll();
		LinkedList<Warranty> listaWarranties = new LinkedList<Warranty>(warranties);
		Warranty warranty = listaWarranties.getFirst();
		Assert.notNull(warranty);
		Assert.isTrue(warranty.getDraftMode()==true);
		warrantyService.deleteWarranty(warranty);
	//	Assert.isTrue(!(warrantyService.findAll().contains(warranty)));
		
	}

}
