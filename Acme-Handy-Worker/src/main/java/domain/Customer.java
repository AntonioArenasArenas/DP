
package domain;

import java.util.Collection;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Endorser {

	
	private Collection<Task>		tasks;

	
	
	@OneToMany
	@Valid
	@NotNull
	public Collection<Task> getTasks() {
		return this.tasks;
	}

	

	public void setTasks(Collection<Task> tasks) {
		this.tasks = tasks;
	}

}
