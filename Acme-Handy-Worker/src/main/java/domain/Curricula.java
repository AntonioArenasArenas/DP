
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Curricula extends DomainEntity {

	private Collection<EndorsementRecord>	endorsementRecords;
	private Collection<EducationalRecord>	educationalRecords;
	private Collection<MiscellaneousRecord>	miscellaneousRecords;
	private PersonalRecord					personalRecord;
	private Collection<ProfessionalRecord>	professionalRecords;
	private String							ticker;


	@OneToMany(cascade = CascadeType.ALL)
	@Valid
	@NotNull
	public Collection<EndorsementRecord> getEndorsementRecords() {
		return this.endorsementRecords;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@Valid
	@NotNull
	public Collection<EducationalRecord> getEducationalRecords() {
		return this.educationalRecords;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@Valid
	@NotNull
	public Collection<MiscellaneousRecord> getMiscellaneousRecords() {
		return this.miscellaneousRecords;
	}

	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@Valid
	@NotNull
	public PersonalRecord getPersonalRecord() {
		return this.personalRecord;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@Valid
	@NotNull
	public Collection<ProfessionalRecord> getProfessionalRecords() {
		return this.professionalRecords;
	}

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^[0-9]{6}-[A-Z0-9]{6}$")
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	public void setEndorsementRecords(final Collection<EndorsementRecord> endorsementRecords) {
		this.endorsementRecords = endorsementRecords;
	}

	public void setEducationalRecords(final Collection<EducationalRecord> educationalRecords) {
		this.educationalRecords = educationalRecords;
	}

	public void setMiscellaneousRecords(final Collection<MiscellaneousRecord> miscellaneousRecords) {
		this.miscellaneousRecords = miscellaneousRecords;
	}

	public void setPersonalRecord(final PersonalRecord personalRecord) {
		this.personalRecord = personalRecord;
	}

	public void setProfessionalRecords(final Collection<ProfessionalRecord> professionalRecords) {
		this.professionalRecords = professionalRecords;
	}

}
