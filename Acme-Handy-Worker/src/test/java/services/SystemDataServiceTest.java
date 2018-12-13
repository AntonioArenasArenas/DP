package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.SystemData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml" })
@Transactional
public class SystemDataServiceTest extends AbstractTest {

    @Autowired
    private SystemDataService systemDataService;


    @Test
    public void testCreateSystemData(){
    	super.authenticate("admin");
       SystemData systemData = systemDataService.create();
        super.unauthenticate();
    }

    @Test
    public void testSaveSystemData(){
        super.authenticate("admin");
        SystemData systemData = systemDataService.create();
        SystemData systemData2 = systemDataService.save(systemData);
        super.unauthenticate();

    }

    @Test
    public void testDeleteSystemData(){
        super.authenticate("admin");
        SystemData systemData = systemDataService.create();
        systemDataService.delete(systemData);
        super.unauthenticate();
    }

    @Test
    public void testGetSystemData(){
        super.authenticate("admin");
        SystemData systemData = systemDataService.getSystemData();
        super.unauthenticate();
    }
}

