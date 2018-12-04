
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity {

	private Collection<Category>	childrenCategories;
	private String					name;
	private String					nameEsp;

	@Valid
	@NotNull
	@OneToMany
	public Collection<Category> getChildrenCategories() {
		return this.childrenCategories;
	}


	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	public String getNameEsp() {
		return this.nameEsp;
	}

	public void setNameEsp(final String nameEsp) {
		this.nameEsp = nameEsp;
	}

	public void setChildrenCategories(final Collection<Category> childrenCategories) {
		this.childrenCategories = childrenCategories;
	}

	

}
