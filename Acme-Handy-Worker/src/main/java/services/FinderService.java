package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;


import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class FinderService {

    @Autowired
    FinderRepository finderRepository;

    // Supporting services ---------------------------------------

    @Autowired
    private SystemDataService systemDataService;
    @Autowired
    WorkerService workerService;
    // Constructors --------------------------------------------------------------------------

    public FinderService(){
        super();
    }

    // Simple CRUD methods ------------------------------------------------

    public Finder create(){

        Finder result;
        result = new Finder();
        return result;
    }

    public Finder save(Finder finder){

        Assert.notNull(finder);
        Date now = new Date();
        Finder result;
        finder.setLastUpdate(now);
        result = finderRepository.save(finder);
        return result;
    }
    public void delete(Finder finder){
    	
    	Assert.notNull(finder);
        finderRepository.delete(finder);

    }
    public Finder findOne() {

        Finder result;
        result = getFinderByWorkerId();
        cleanFinderCache();
        return result;
    }
    public void cleanFinderCache() {
    	
        Finder finder = getFinderByWorkerId();
        Long lastUpdate;
        Long now;
        SystemData s;
        Long passedTime;

        lastUpdate = finder.getLastUpdate().getTime();
        now = new Date(System.currentTimeMillis()).getTime();
        passedTime = (now - lastUpdate) / 1000;
        passedTime = passedTime / 3600;
        s = systemDataService.getSystemData();
        Collection<Task> cleaned = new ArrayList<>();
        if (passedTime >= s.getCache()){
            finder.setTasks(cleaned);
        }
    }
    public Finder getFinderByWorkerId(){
    	
        Finder result;
        Worker logged;
        logged = workerService.findByPrincipal();
        result = finderRepository.getFinderByWorkerId(logged.getId());
        Assert.notNull(result);
        return result;
    }
    public void setCache(int cache){
        SystemData s;
        s = this.systemDataService.getSystemData();
        s.setCache(cache);

    }
}
