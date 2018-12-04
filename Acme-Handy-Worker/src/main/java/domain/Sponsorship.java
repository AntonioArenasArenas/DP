
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.URL;
@Entity
@Access(AccessType.PROPERTY)
public class Sponsorship extends DomainEntity {

	private Sponsor		sponsor;
	private Tutorial	tutorial;
	private String		banner;
	private String		link;
	private CreditCard	creditCard;

	@ManyToOne(optional=false)
	public Sponsor getSponsor() {
		return this.sponsor;
	}
	@ManyToOne(optional=false)
	public Tutorial getTutorial() {
		return this.tutorial;
	}

	@URL
	public String getBanner() {
		return this.banner;
	}

	public void setBanner(final String banner) {
		this.banner = banner;
	}
	@URL
	public String getLink() {
		return this.link;
	}

	public void setLink(final String link) {
		this.link = link;
	}

	@OneToOne(optional=false)
	@Valid
	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	public void setTutorial(Tutorial tutorial) {
		this.tutorial = tutorial;
	}

}
