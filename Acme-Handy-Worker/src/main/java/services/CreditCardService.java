package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.CreditCardRepository;
import domain.CreditCard;

@Service
@Transactional
public class CreditCardService {

	@Autowired
	private CreditCardRepository creditCardRepository;

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public CreditCardService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public CreditCard save(CreditCard creditCard) {

		CreditCard result;

		result = creditCardRepository.save(creditCard);

		return result;
	}


}
