
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Box extends DomainEntity {

	private Collection<Message>	messages;
	private String				name;
	private boolean				isDefault;
	
	@Valid
	@NotNull
	@ManyToMany
	public Collection<Message> getMessages() {
		return this.messages;
	}

	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public boolean getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(final Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public void setMessages(Collection<Message> messages) {
		this.messages = messages;
	}

}
