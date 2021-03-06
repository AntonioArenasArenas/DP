
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Complaint extends DomainEntity {

	private Report				report;
	private String				ticker;
	private Date				moment;
	private String				description;
	private Collection<String>	attachments;
	private Referee				referee;

	
	@ManyToOne(optional=true)
	public Referee getReferee() {
		return this.referee;
	}
	
	@OneToOne(mappedBy = "complaint", optional = true)
	public Report getReport() {
		return this.report;
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
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}
	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@ElementCollection
	public Collection<String> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(final Collection<String> attachments) {
		this.attachments = attachments;
	}

	public void setReport(final Report report) {
		this.report = report;
	}
	
	public void setReferee(final Referee referee) {
		this.referee = referee;
	}
	
	// Complaint -> Task
	 private Task task;
	
	@ManyToOne(optional = false, cascade = CascadeType.PERSIST)
	public Task getTask() {
		return this.task;
	}
	public void setTask(final Task task) {
		this.task = task;
	}

}
