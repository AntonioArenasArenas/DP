package services;

import java.util.ArrayList;
import java.util.Collection;
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
	private WorkerService workerService;

	@Autowired
	private TaskService taskService;

	// Constructors
	// --------------------------------------------------------------------------

	public FinderService() {
		super();
	}

	// Simple CRUD methods ------------------------------------------------

	public Finder create() {

		Finder result;
		result = new Finder();
		Collection<Task> voidLisOfTasks = new ArrayList<>();
		result.setTasks(voidLisOfTasks);
		return result;
	}

	public Finder save(Finder finder) {

		Assert.notNull(finder);
		Date now = new Date();
		Finder result;
		finder.setLastUpdate(now);
		finder.setTasks(this.getTasksByFinderFilter(finder.getCategory(),
				finder.getWarranty(), finder.getMaxPrice(),
				finder.getMinPrice(), finder.getStartDate(),
				finder.getEndDate(), finder.getKeyWord()));
		result = finderRepository.save(finder);
		return result;
	}

	public Finder save2(Finder finder) {
		Assert.notNull(finder);
		Finder finderSaved = this.finderRepository.save(finder);
		return finderSaved;
	}

	public void delete(Finder finder) {

		Assert.notNull(finder);
		finderRepository.delete(finder);
		Assert.isNull(finder);

	}

	public Finder getFinderOfLogged() {

		Finder result;
		result = getFinderByLoggedWorker();
		Worker logged = workerService.findByPrincipal();
		Assert.isTrue(logged.getFinder() == result);
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
		if (passedTime >= s.getCache()) {
			finder.setTasks(cleaned);
		}
	}

	public Finder getFinderByLoggedWorker() {

		Finder result;
		Worker logged;
		logged = workerService.findByPrincipal();
		result = finderRepository.getFinderByLoggedWorker(logged.getId());
		Assert.notNull(result);
		return result;
	}

	public void setCache(int cache) {

		SystemData s;
		s = this.systemDataService.getSystemData();
		s.setCache(cache);
		Assert.isTrue(s.getCache() == cache);

	}

	public Collection<Task> getTasksByFinderFilter(String category,
			String warranty, Double maxPrice, Double minPrice, Date startDate,
			Date endDate, String keyWord) {
		Collection<Task> result;

		if (!(category == null || category.isEmpty())) {
			result = finderRepository.filterTasksByCategory(category);
		} else {
			result = taskService.getActiveTasks();
		}

		if (!(warranty == null || warranty.isEmpty())) {
			Collection<Task> tasksFilteredByWar = finderRepository
					.filterTasksByWarranty(warranty);
			result.retainAll(tasksFilteredByWar);
		}
		if (maxPrice != null) {
			Collection<Task> tasksFilteredByMaxPrice = finderRepository
					.filterTasksByMaxPrice(maxPrice);
			result.retainAll(tasksFilteredByMaxPrice);
		}

		if (minPrice != null) {
			Collection<Task> tasksFilteredByMinPrice = finderRepository
					.filterTasksByMinPrice(minPrice);
			result.retainAll(tasksFilteredByMinPrice);
		}
		if (startDate != null) {
			Collection<Task> tasksFilteredByStartDate = finderRepository
					.filterTasksByStartDate(startDate);
			result.retainAll(tasksFilteredByStartDate);
		}
		if (endDate != null) {
			Collection<Task> tasksFilteredByEndDate = finderRepository
					.filterTasksByEndDate(endDate);
			result.retainAll(tasksFilteredByEndDate);
		}

		if (!(keyWord == null || keyWord.isEmpty())) {
			Collection<Task> taskFilteredByKeyWordInDescription = finderRepository
					.filterTasksByKeyWordInDescription(keyWord);
			Collection<Task> taskFilteredByKeyWordInTicker = finderRepository
					.filterTasksByKeyWordInTicker(keyWord);
			Collection<Task> taskFilteredByKeyWordInAddress = finderRepository
					.filterTasksByKeyWordInAddress(keyWord);

			taskFilteredByKeyWordInTicker
					.removeAll(taskFilteredByKeyWordInDescription);
			taskFilteredByKeyWordInDescription
					.addAll(taskFilteredByKeyWordInTicker);
			taskFilteredByKeyWordInAddress
					.removeAll(taskFilteredByKeyWordInDescription);
			taskFilteredByKeyWordInDescription
					.addAll(taskFilteredByKeyWordInAddress);

			result.retainAll(taskFilteredByKeyWordInDescription);

		}

		return result;
	}
}
