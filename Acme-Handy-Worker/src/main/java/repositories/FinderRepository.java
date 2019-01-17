package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import domain.Finder;
import domain.Task;


public interface FinderRepository extends JpaRepository<Finder, Integer>{

    @Query("select w.finder from Worker w where w.id=?1")
    public Finder getFinderByLoggedWorker(int workerId);

    @Query("select t from Task t where (t.category.name=?1 or t.category.nameEsp=?1) and t.endDate>CURRENT_DATE")
    public Collection<Task> filterTasksByCategory(String category);

    @Query("select t from Task t where t.warranty.title=?1 and t.endDate>CURRENT_DATE")
    public Collection<Task> filterTasksByWarranty(String warranty);

    @Query("select t from Task t where t.maxPrice<=?1 and t.endDate>CURRENT_DATE")
    public Collection<Task> filterTasksByMaxPrice(Double maxPrice);

    @Query("select t from Task t where t.maxPrice>=?1 and t.endDate>CURRENT_DATE")
    public Collection<Task> filterTasksByMinPrice(Double minPrice);

    @Query("select t from Task t")
    public Collection<Task> getAllTasks();

    @Query("select t from Task t where t.description like %?1% and t.endDate>CURRENT_DATE")
    public Collection<Task> filterTasksByKeyWordInDescription(String keyWord);

    @Query("select t from Task t where t.ticker like %?1% and t.endDate>CURRENT_DATE")
    public Collection<Task> filterTasksByKeyWordInTicker(String keyWord);

    @Query("select t from Task t where t.address like %?1% and t.endDate>CURRENT_DATE")
    public Collection<Task> filterTasksByKeyWordInAddress(String keyWord);

    @Query("select t from Task t where t.startDate>=?1 and t.endDate>CURRENT_DATE ")
    public Collection<Task> filterTasksByStartDate(Date startDate);

    @Query("select t from Task t where t.endDate<=?1 and t.endDate>CURRENT_DATE")
    public Collection<Task> filterTasksByEndDate(Date endDate);



}
