package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import controllers.AbstractController;
import domain.Category;

@Controller
@RequestMapping("/category/administrator")
public class CategoryAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private CategoryService categoryService;

	// Constructors -----------------------------------------------------------

	public CategoryAdministratorController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int categoryId,
			@CookieValue("language") final String lang) {
		ModelAndView result;
		Collection<Category> categories;
		Category parent;

		if (categoryId == 0) {
			parent = categoryService.getRootCategory();

		} else {
			try {
				parent = categoryService.findOne(categoryId);
			} catch (Throwable oops) {
				return result = new ModelAndView(
						"redirect:list.do?categoryId=0");
			}
		}
		categories = parent.getChildrenCategories();
		result = new ModelAndView("category/list");
		result.addObject("categories", categories);
		result.addObject("parentCategory", parent);
		result.addObject("language", lang);
		return result;

	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam int categoryId,
			@CookieValue("language") final String lang) {
		ModelAndView result;

		Category category;

		try {
			category = categoryService.findOne(categoryId);
		} catch (Throwable oops) {
			return result = new ModelAndView("redirect:list.do?categoryId=0");
		}

		result = new ModelAndView("category/show");
		result.addObject("category", category);
		result.addObject("language", lang);

		return result;

	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Category category;

		category = this.categoryService.createCategory();

		result = this.createEditModelAndView(category);
		return result;

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid Category category,
			BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = this.createEditModelAndView(category);
		} else {
			try {
				Category saved = categoryService.save(category);
				Category parent = categoryService.findOne(saved.getParent()
						.getId());

				parent.getChildrenCategories().add(saved);

				categoryService.save(parent);

				result = new ModelAndView("redirect:list.do?categoryId=0");

			} catch (Throwable oops) {
				result = this.createEditModelAndView(category,
						"administrator.commit.error");

			}
		}

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int categoryId) {
		ModelAndView result;
		Category category;

		try {
			category = categoryService.findOne(categoryId);
		} catch (Throwable oops) {
			return result = new ModelAndView("redirect:list.do?categoryId=0");
		}
		Assert.notNull(category);

		result = this.createEditModelAndView(category);
		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid Category category, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = this.createEditModelAndView(category);
		} else {
			try {

				Category saved = categoryService.findOne(category.getId());

				if (!saved.getParent().equals(category.getParent())) {

					Category parent = categoryService.findOne(saved.getParent()
							.getId());

					parent.getChildrenCategories().remove(saved);

					categoryService.save(category);
					categoryService.save(parent);
				} else {
					categoryService.save(category);
				}
				result = new ModelAndView("redirect:list.do?categoryId=0");

			} catch (Throwable oops) {
				result = this.createEditModelAndView(category,
						"administrator.commit.error");

			}
		}

		return result;

	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int categoryId) {
		ModelAndView result;
		Category category;
		try {
			category = categoryService.findOne(categoryId);
			if (category.equals(categoryService.getRootCategory())) {
				result = new ModelAndView("redirect:list.do?categoryId=0");
			}
		} catch (Throwable oops) {
			return result = new ModelAndView("redirect:list.do?categoryId=0");
		}
		try {

			categoryService.delete(category);

			result = new ModelAndView("redirect:list.do?categoryId=0");

		} catch (Throwable oops) {
			result = new ModelAndView("redirect:list.do?categoryId=0");

		}

		return result;

	}

	protected ModelAndView createEditModelAndView(Category category) {
		ModelAndView result;
		result = createEditModelAndView(category, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Category category,
			String messageCode) {
		ModelAndView result;
		Collection<Category> categories = this.categoryService.findAll();
		if (category.getId() == 0) {
			result = new ModelAndView("category/create");
			result.addObject("requestURI", "category/administrator/create.do");
		} else {
			result = new ModelAndView("category/create");
			result.addObject("requestURI", "category/administrator/edit.do");
		}
		result.addObject("category", category);
		result.addObject("categories", categories);
		return result;
	}

}
