package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CurriculaRepository;
import domain.Actor;
import domain.Curricula;
import domain.Message;

@Service
@Transactional
public class CurriculaService {
	
	// Managed repository -----------------------------------------------------

		@Autowired
		private CurriculaRepository curriculaRepository;

		// Supporting services ----------------------------------------------------
		@Autowired
		private ActorService actorService;

		// Constructors -----------------------------------------------------------

		public CurriculaService() {
			super();
		}

		// Simple CRUD methods ----------------------------------------------------
		// ---
		public Curricula create() {
			Curricula result;
			result = new Curricula();

//			result.setEducationalRecords(educationalRecords);
//			result.setEndorsementRecords(endorsementRecords);
//			result.setMiscellaneousRecords(miscellaneousRecords);
//			result.setPersonalRecord(personalRecord);
//			result.setProfessionalRecords(professionalRecords);
//			result.setTicker(ticker);


			return result;
		}
		
		public Curricula save(Curricula curricula) {

			Assert.notNull(curricula);	
			Curricula result;

			result = curriculaRepository.save(curricula);
			
			
			return result;
		}

}
