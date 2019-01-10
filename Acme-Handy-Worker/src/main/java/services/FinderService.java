package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import domain.Finder;
import domain.SystemData;
import domain.Task;
import domain.Worker;

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

    public Finder save(Finder finder, String category, String warranty, Double maxPrice, String keyWord ){

        Assert.notNull(finder);
        Date now = new Date();
        Finder result;
        finder.setLastUpdate(now);
        finder.setTasks(finderService.getTasksByFinderFilter(category, warranty, maxPrice, keyWord));
        result = finderRepository.save(finder);
        return result;
    }
    public void delete(Finder finder){

    	  Assert.notNull(finder);
        finderRepository.delete(finder);
        Assert.isNull(finder);

    }
    public Finder getFinderOfLogged() {

        Finder result;
        result = getFinderByLoggedWorker();
        cleanFinderCache();
        return result;
    }
    public void cleanFinderCache() {

        Finder finder = getFinderByLoggedWorker();
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
    public Finder getFinderByLoggedWorker(){

        Finder result;
        Worker logged;
        logged = workerService.findByPrincipal();
        result = finderRepository.getFinderByLoggedWorker(logged.getId());
        Assert.notNull(result);
        return result;
    }
    public void setCache(int cache){

        SystemData s;
        s = this.systemDataService.getSystemData();
        s.setCache(cache);
        Assert.isTrue(s.getCache() == cache);

    }

    public Collection<Task> getTasksByFinderFilter(String category, String warranty, Double maxPrice, String keyWord){
    	Collection<Task> result;

    	if(!category.equals("")) {
    		result = finderRepository.filterTasksByCategory(category);
    	} else {
    		result = finderRepository.getAllTasks();
    	}

    	if(!warranty.equals("")) {
    		Collection<Task> tasksFilteredByWar = finderRepository.filterTasksByWarranty(warranty);
    		result.retainAll(tasksFilteredByWar);
    	}
    	if(maxPrice != null){
    		Collection<Task> tasksFilteredByMaxPrice = finderRepository.filterTasksByMaxPrice(maxPrice);
    		result.retainAll(tasksFilteredByMaxPrice);
    	}
    	if(!keyWord.equals("")){
        Collection<Task> taskFilteredByKeyWordInDescription = finderRepository.filterTasksByKeyWordInDescription(keyWord);
        Collection<Task> taskFilteredByKeyWordInTicker = finderRepository.filterTasksByKeyWordInTicker(keyWord);
        Collection<Task> taskFilteredByKeyWordInAddress = finderRepository.filterTasksByKeyWordInAddress(keyWord);

        taskFilteredByKeyWordInTicker.removeAll(taskFilteredByKeyWordInDescription);
        taskFilteredByKeyWordInDescription.addAll(taskFilteredByKeyWordInTicker);
        taskFilteredByKeyWordInAddress.removeAll(taskFilteredByKeyWordInDescription);
        taskFilteredByKeyWordInDescription.addAll(taskFilteredByKeyWordInAddress);

        result.retainAll(taskFilteredByKeyWordInDescription);

    	}

    	return result;
    }
}
