package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class SystemData extends DomainEntity {

	private String name;
	private String banner;
	private String bannerHeader;
	private String welcomePageMsg;
	private List<String> phoneCode;
	private List<String> spamWords;
	private String vatPercentage;
	private int cache;
	private int maxPrice;
	
	
	

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	public int getCache() {
		return this.cache;
	}

	public void setCache(final int cache) {
		this.cache = cache;
	}

	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	public String getBanner() {
		return this.banner;
	}

	public void setBanner(final String banner) {
		this.banner = banner;
	}

	@NotBlank
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
	@ElementCollection
	public List<String> getPhoneCode() {
		return this.phoneCode;
	}

	public void setPhoneCode(final List<String> phoneCode) {
		this.phoneCode = phoneCode;
	}

	@NotNull
	@ElementCollection
	public List<String> getSpamWords() {
		return this.spamWords;
	}

	public void setSpamWords(final List<String> spamWords) {
		this.spamWords = spamWords;
	}

	@NotBlank
	public String getVatPercentage() {
		return this.vatPercentage;
	}

	public void setVatPercentage(final String vatPercentage) {
		this.vatPercentage = vatPercentage;
	}

}
