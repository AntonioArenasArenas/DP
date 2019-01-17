package services;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
        "classpath:spring/config/packages.xml" })
@Transactional
public class FinderServiceTest extends AbstractTest {

//    @Autowired
//    private FinderService finderService;
//
//
//    @Test
//    public void testCreateFinder(){
//    	super.authenticate("admin");
//        Finder finder = finderService.create();
//        super.unauthenticate();
//    }
//
//    @Test
//    public void testGetFinderByWorkerId(){
//        super.authenticate("admin");
//        Finder finder;
//        finder = finderService.getFinderByWorkerId();
//        super.unauthenticate();
//
//    }
//
//    @Test
//    public void testFindOne(){
//        super.authenticate("admin");
//        Finder finder = finderService.findOne();
//        super.unauthenticate();
//    }
//
//    @Test
//    public void testCleanFinderCache(){
//        super.authenticate("admin");
//        finderService.cleanFinderCache();
//        super.unauthenticate();
//    }
//    
//    @Test
//    public void testSetCache(){
//    	super.authenticate("admin");
//    	finderService.setCache(5);
//    	super.unauthenticate();
//    }

}

