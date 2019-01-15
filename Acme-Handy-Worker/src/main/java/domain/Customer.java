
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Endorser {
	
	private Collection<Task> tasks;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
	public Collection<Task> getTasks(){
		return tasks;
	}
	
	public void setTasks(Collection<Task> tasks){
		this.tasks = tasks;
	}

}
