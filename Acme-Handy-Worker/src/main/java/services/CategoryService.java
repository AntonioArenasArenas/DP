package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Category;

import repositories.CategoryRepository;


@Service
@Transactional
public class CategoryService {

	
	// Managed repository -----------------------------------------------------
	@Autowired
	private CategoryRepository		categoryRepository;
	
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

		return result;
	}

	public Category save(Category category) {

		Assert.notNull(category);

		Category result;

		result = categoryRepository.save(category);

		return result;
	}
}
