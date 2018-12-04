
package domain;

import java.util.Collection;



import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Worker extends Endorser {
	
	private Curricula				curricula;
	private Collection<Tutorial>	tutorials;
	private Collection<Application>	applications;
	private Finder			        finder;
	private String					make;

	@OneToOne(optional=false)
	@Valid
	@NotNull
	public Curricula getCurricula() {
		return this.curricula;
	}
	
	@OneToMany
	@Valid
	@NotNull
	public Collection<Tutorial> getTutorials() {
		return this.tutorials;
	}
	
	@OneToMany(mappedBy="worker")
	@Valid
	@NotNull
	public Collection<Application> getApplications() {
		return this.applications;
	}
	
	@OneToOne(optional=true)
	public Finder   getFinder() {
		return this.finder;
	}
	
	@NotBlank
	public String getMake() {
		return this.make;
	}

	public void setMake(final String make) {
		this.make = make;
	}

	public void setCurricula(Curricula curricula) {
		this.curricula = curricula;
	}

	public void setTutorials(Collection<Tutorial> tutorials) {
		this.tutorials = tutorials;
	}

	public void setApplications(Collection<Application> applications) {
		this.applications = applications;
	}


	public void setFinder(Finder finder) {
		this.finder = finder;
	}

}
