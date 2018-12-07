
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Task extends DomainEntity {

	private Collection<Complaint>	complaints;
	private Collection<Phase>		phases;
	private Warranty				warranty;
	private Category				category;
	private Collection<Application>	applications;
	private String					ticker;
	private Date					moment;
	private Date					startDate;

	private Date					endDate;
	private String					description;
	private String					address;
	private double					maxPrice;
	private String					comments;


	@OneToMany
	@Valid
	@NotNull
	public Collection<Complaint> getComplaints() {
		return this.complaints;
	}
	
	@OneToMany
	@Valid
	@NotNull
	public Collection<Phase> getPhases() {
		return this.phases;
	}

	@ManyToOne(optional = false)
	public Warranty getWarranty() {
		return this.warranty;
	}

	@ManyToOne(optional = false)
	public Category getCategory() {
		return this.category;
	}

	@OneToMany(mappedBy = "task")
	@Valid
	@NotNull
	public Collection<Application> getApplications() {
		return this.applications;
	}
	
	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "^[0-9]{6}-[A-Z0-9]{6}$")
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}
	@Temporal(TemporalType.DATE)
	public Date getStartDate() {
		return this.startDate;
	}
	@Temporal(TemporalType.DATE)
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}
	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}
	@Digits(fraction = 2, integer = 3)
	public double getMaxPrice() {
		return this.maxPrice;
	}

	public void setMaxPrice(final double maxPrice) {
		this.maxPrice = maxPrice;
	}
	@NotBlank
	public String getComments() {
		return this.comments;
	}

	public void setComments(final String comments) {
		this.comments = comments;
	}

	public void setComplaints(final Collection<Complaint> complaints) {
		this.complaints = complaints;
	}

	public void setPhases(final Collection<Phase> phases) {
		this.phases = phases;
	}

	public void setWarranty(final Warranty warranty) {
		this.warranty = warranty;
	}

	public void setCategory(final Category category) {
		this.category = category;
	}

	public void setApplications(final Collection<Application> applications) {
		this.applications = applications;
	}

}
