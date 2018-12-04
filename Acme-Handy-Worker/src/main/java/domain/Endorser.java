
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Endorser extends Actor {

	private Collection<Endorsement>	endorsements;
	
	@Valid
	@OneToMany
	@NotNull
	public Collection<Endorsement> getEndorsements() {
		return this.endorsements;
	}

	
	public void setEndorsements(Collection<Endorsement> endorsements) {
		this.endorsements = endorsements;
	}

}
