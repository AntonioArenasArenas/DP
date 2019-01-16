package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity {

	private Collection<Category> childrenCategories;
	private String name;
	private String nameEsp;
	private Category parent;

	@Valid
	@NotNull
	@OneToMany(mappedBy = "parent")
	public Collection<Category> getChildrenCategories() {
		return this.childrenCategories;
	}

	@Valid
	@ManyToOne(optional = true)
	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	@Column(unique = true)
	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Column(unique = true)
	@NotBlank
	public String getNameEsp() {
		return this.nameEsp;
	}

	public void setNameEsp(final String nameEsp) {
		this.nameEsp = nameEsp;
	}

	public void setChildrenCategories(
			final Collection<Category> childrenCategories) {
		this.childrenCategories = childrenCategories;
	}

}
