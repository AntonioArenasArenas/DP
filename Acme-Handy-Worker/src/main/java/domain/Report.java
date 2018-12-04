
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Report extends DomainEntity {

	private Complaint			complaint;
	private Referee				referee;
	private Collection<Note>	notes;
	private Date				moment;
	private String				description;
	private Collection<String>	attachments;
	private boolean				finalReport;


	@ElementCollection
	public Collection<String> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(final Collection<String> attachments) {
		this.attachments = attachments;
	}

	@OneToOne(optional = false)
	public Complaint getComplaint() {
		return this.complaint;
	}

	@ManyToOne(optional=false)
	public Referee getReferee() {
		return this.referee;
	}
	
	@OneToMany(cascade = CascadeType.ALL)
	@Valid
	@NotNull
	public Collection<Note> getNotes() {
		return this.notes;
	}

	@Past
	@Temporal(TemporalType.TIMESTAMP)
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

	public void setComplaint(final Complaint complaint) {
		this.complaint = complaint;
	}

	public void setReferee(final Referee referee) {
		this.referee = referee;
	}

	public void setNotes(final Collection<Note> notes) {
		this.notes = notes;
	}

	public boolean isFinalReport() {
		return this.finalReport;
	}

	public void setFinalReport(final boolean finalReport) {
		this.finalReport = finalReport;
	}

}
