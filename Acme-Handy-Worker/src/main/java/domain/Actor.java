
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import security.UserAccount;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Access(AccessType.PROPERTY)
public abstract class Actor extends DomainEntity {

	private Collection<Box>		boxes;

	private Collection<Message>	sentMessages;

	private Collection<Message>	receivedMessages;

	private Collection<Profile>	profiles;

	private String				name;
	private String				surname;
	private String				middleName;
	private String				photo;
	private String				email;
	private String				phoneNumber;
	private String				address;
	private boolean				suspicious;
	private UserAccount       	userAccount;
	

	
	

	@Valid
	@NotNull
	@OneToMany
	public Collection<Profile> getProfiles() {
		return this.profiles;
	}
	
	
	@NotNull
	@OneToOne(optional=false)
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
	@Valid
	@NotNull
	@NotEmpty
	@OneToMany
	public Collection<Box> getBoxes() {
		return this.boxes;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy = "sender")
	public Collection<Message> getSentMessages() {
		return this.sentMessages;
	}
	
	@Valid
	@NotNull
	@ManyToMany(mappedBy = "recipients")
	public Collection<Message> getReceivedMessages() {
		return this.receivedMessages;
	}

	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}
	@NotBlank
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	@URL
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(final String photo) {
		this.photo = photo;
	}

	@NotBlank
	@Email
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}
	@Pattern(regexp = "^\\+[1-9][0-9 ]+$")
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}
	
	public boolean isSuspicious() {
		return suspicious;
	}

	public void setSuspicious(boolean suspicious) {
		this.suspicious = suspicious;
	}

	public void setBoxes(final Collection<Box> boxes) {
		this.boxes = boxes;
	}

	public void setSentMessages(final Collection<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}

	public void setReceivedMessages(final Collection<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}

	public void setProfiles(final Collection<Profile> profiles) {
		this.profiles = profiles;
	}

}
