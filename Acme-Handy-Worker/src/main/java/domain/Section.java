
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Section extends DomainEntity {

	private int				sectioniD;
	private String				title;
	private String				text;
	private Collection<String>	picture;


	@NotNull
	@Column(unique = true)
	public int getSectioniD() {
		return this.sectioniD;
	}

	public void setSectioniD(final int sectioniD) {
		this.sectioniD = sectioniD;
	}

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getText() {
		return this.text;
	}

	public void setText(final String text) {
		this.text = text;
	}
	
	@ElementCollection
	public Collection<String> getPicture() {
		return this.picture;
	}

	public void setPicture(final Collection<String> picture) {
		this.picture = picture;
	}

}
