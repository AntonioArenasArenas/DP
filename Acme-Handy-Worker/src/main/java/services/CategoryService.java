package services;

import java.util.Collection;
import java.util.LinkedList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import domain.Category;
import domain.Task;

@Service
@Transactional
public class CategoryService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private TaskService taskService;

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public CategoryService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Collection<Category> findAll() {

		return categoryRepository.findAll();

	}

	public Category findOne(int categoryId) {
		Assert.isTrue(categoryId != 0);

		Category result;

		result = categoryRepository.findOne(categoryId);
		Assert.notNull(result);

		return result;
	}

	public Category createCategory() {

		Category result;

		result = new Category();

		Collection<Category> children = new LinkedList<Category>();
		result.setChildrenCategories(children);

		return result;
	}

	public Category save(Category category) {

		Assert.notNull(category);

		Category result;

		result = categoryRepository.save(category);

		return result;
	}

	public Category saveSubCategory(Category parent, Category children) {

		Assert.notNull(parent);

		Assert.notNull(children);

		Category result;

		result = categoryRepository.save(children);

		parent.getChildrenCategories().add(result);

		categoryRepository.save(parent);

		return result;

	}

	public Category getRootCategory() {

		Category result;

		result = categoryRepository.getRootCategory();

		return result;
	}

	public void delete(Category category) {

		Assert.notNull(category);

		Assert.isTrue(categoryRepository.findAll().contains(category));

		Collection<Task> tasks = taskService.findAll();
		// Borrar categorias hijas y sus referencias en task
		for (Category c : category.getChildrenCategories()) {

			for (Task t : tasks) {

				if (t.getCategory().equals(c)) {
					t.setCategory(this.getRootCategory());
					taskService.save(t);
				}
			}
			categoryRepository.delete(c);
		}

		// Borrar categoria principal y su referencia en task
		for (Task t : tasks) {

			if (t.getCategory().equals(category)) {
				t.setCategory(this.getRootCategory());
				taskService.save(t);
			}
		}

		categoryRepository.delete(category);
	}
}
