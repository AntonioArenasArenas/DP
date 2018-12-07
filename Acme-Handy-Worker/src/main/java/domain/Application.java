
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

@Entity
@Access(AccessType.PROPERTY)
public class Application extends DomainEntity {

	private Worker		worker;		//nav
	private Task		task;			//nav
	private Date		moment;
	private String		status;
	private double		offeredPrize;
	private String		comments;
	private CreditCard	creditCard;


	@ManyToOne(optional = false)
	public Worker getWorker() {
		return this.worker;
	}
	@ManyToOne(optional = false)
	public Task getTask() {
		return this.task;
	}
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}
	@Pattern(regexp = "^\\b(PENDING|ACCEPTED|REJECTED)\\b")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}
	@Digits(fraction = 2, integer = 3)
	@Min(value = 0)
	public double getOfferedPrize() {
		return this.offeredPrize;
	}

	public void setOfferedPrize(final double offeredPrize) {
		this.offeredPrize = offeredPrize;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(final String comments) {
		this.comments = comments;
	}

	@Valid
	@OneToOne(optional=true)
	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public void setWorker(final Worker worker) {
		this.worker = worker;
	}

	public void setTask(final Task task) {
		this.task = task;
	}

}
