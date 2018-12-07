package services;

import domain.Finder;
import domain.SystemData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;


import javax.transaction.Transactional;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml" })
@Transactional
public class FinderServiceTest extends AbstractTest {

    @Autowired
    private FinderService finderService;

    @Autowired
    private SystemDataService systemDataService;


    @Test
    public void testCreateFinder(){
    	super.authenticate("admin");
        Finder finder = finderService.create();
        super.unauthenticate();
    }

    @Test
    public void testGetFinderByWorkerId(){
        super.authenticate("admin");
        Finder finder;
        finder = finderService.getFinderByWorkerId();
        super.unauthenticate();

    }

    @Test
    public void testFindOne(){
        super.authenticate("admin");
        Finder finder = finderService.findOne();
        super.unauthenticate();
    }

    @Test
    public void testCleanFinderCache(){
        super.authenticate("admin");
        finderService.cleanFinderCache();
        super.unauthenticate();
    }
    
    @Test
    public void testSetCache(){
    	super.authenticate("admin");
    	finderService.setCache(5);
    	super.unauthenticate();
    }

    @SuppressWarnings("deprecation")
	@Test
    public void testChangeFinderFilters(){
        super.authenticate("admin");
        finderService.changeFinderFilters("hola",
                "hola2",
                "jaja",
                33.2,
                30.3,
                new Date(1998,01,23),
                new Date(1998,02,23),
                new Date(1998,01,25)
                );
        super.unauthenticate();
    }

}

