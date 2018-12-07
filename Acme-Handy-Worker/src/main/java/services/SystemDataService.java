package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SystemDataRepository;

import domain.Actor;
import domain.Admin;

import domain.SystemData;


@Service
@Transactional
public class SystemDataService {
    // Managed repository -----------------------------------------------------

    @Autowired
    private SystemDataRepository		systemDataRepository;

    // Supporting services ----------------------------------------------------

    @Autowired
    private ActorService actorService;

    // Constructors -----------------------------------------------------------

    public SystemDataService() {
        super();
    }

    // Simple CRUD methods ----------------------------------------------------

    public SystemData create() {
        SystemData result;

        result = new SystemData();
        return result;
    }

    public SystemData save(SystemData systemData) {


        Assert.notNull(systemData);

        SystemData result;

        result = systemDataRepository.save(systemData);

        return result;
    }


    public void delete(SystemData systemData) {

            systemDataRepository.delete(systemData);
        
    }

    // Other business methods -------------------------------------------------

    public SystemData getSystemData(){
        SystemData result;
        result = systemDataRepository.getSystemData();
        return result;
    }



}
