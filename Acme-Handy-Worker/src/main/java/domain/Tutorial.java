
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Access(AccessType.PROPERTY)
public class Tutorial extends DomainEntity {

	private Collection<Sponsorship>	sponsorships;
	private Collection<Section>		sections;
	private String					title;
	private Date					lastUpdate;
	private String					summary;
	private Collection<String>		picture;

	@OneToMany(mappedBy="tutorial")
	@Valid
	@NotNull
	public Collection<Sponsorship> getSponsorships() {
		return this.sponsorships;

	}

	@OneToMany(cascade = CascadeType.ALL)
	@Valid
	@NotNull
	public Collection<Section> getSections() {
		return this.sections;
	}

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}
	@Past
	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(final Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(final String summary) {
		this.summary = summary;
	}
	
	
	@ElementCollection
	public Collection<String> getPicture() {
		return this.picture;
	}

	public void setPicture(final Collection<String> picture) {
		this.picture = picture;
	}

	public void setSponsorships(Collection<Sponsorship> sponsorships) {
		this.sponsorships = sponsorships;
	}

	public void setSections(Collection<Section> sections) {
		this.sections = sections;
	}

}
