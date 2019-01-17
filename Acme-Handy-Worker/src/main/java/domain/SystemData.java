package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class SystemData extends DomainEntity {

	private String name;
	private String bannerHeader;
	private String welcomePageMsg;
	private String welcomePageMsgESP;
	private String phoneCode;
	private List<String> spamWords;
	private double vatPercentage;
	private int maxPrice;
	private List<String> makeCreditCards;
	private Integer cache;
	
	
	@Min(value = 1)
	@Max(value = 2048)
	public Integer getCache() {
		return cache;
	}

	public void setCache(Integer cache) {
		this.cache = cache;
	}

	@NotBlank
	public String getWelcomePageMsgESP() {
		return welcomePageMsgESP;
	}

	public void setWelcomePageMsgESP(String welcomePageMsgESP) {
		this.welcomePageMsgESP = welcomePageMsgESP;
	}

	@ElementCollection
	public List<String> getMakeCreditCards() {
		return makeCreditCards;
	}

	public void setMakeCreditCards(List<String> makeCreditCards) {
		this.makeCreditCards = makeCreditCards;
	}
	
	
	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}


	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@URL
	public String getBannerHeader() {
		return this.bannerHeader;
	}

	public void setBannerHeader(final String bannerHeader) {
		this.bannerHeader = bannerHeader;
	}

	@NotBlank
	public String getWelcomePageMsg() {
		return this.welcomePageMsg;
	}

	public void setWelcomePageMsg(final String welcomePageMsg) {
		this.welcomePageMsg = welcomePageMsg;
	}

	@NotBlank
	@Pattern(regexp = "^\\+[0-9]{1,3}$" )
	public String getPhoneCode() {
		return this.phoneCode;
	}

	public void setPhoneCode(final String phoneCode) {
		this.phoneCode = phoneCode;
	}

	@ElementCollection
	public List<String> getSpamWords() {
		return this.spamWords;
	}

	public void setSpamWords(final List<String> spamWords) {
		this.spamWords = spamWords;
	}

	@Digits(fraction = 2, integer = 2)
	@Min(value = 0)
	public double getVatPercentage() {
		return this.vatPercentage;
	}

	public void setVatPercentage(final double vatPercentage) {
		this.vatPercentage = vatPercentage;
	}

}
