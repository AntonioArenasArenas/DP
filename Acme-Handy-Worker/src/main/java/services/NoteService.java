package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.NoteRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Note;
import domain.Customer;
import domain.Referee;
import domain.Report;
import domain.Worker;

@Service
@Transactional
public class NoteService {

	@Autowired
	private NoteRepository noteRepository;

	// Supporting services ----------------------------------------------------
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private ComplaintService complaintService;
	
	@Autowired
	private ReportService reportService;

	// Constructors -----------------------------------------------------------

	public NoteService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Collection<Note> findAll() {
		Collection<Note> result;

		result = noteRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Note findOne(int noteId) {
		Assert.isTrue(noteId != 0);

		Note result;

		result = noteRepository.findOne(noteId);
		Assert.notNull(result);

		return result;
	}

	public Note createNote(Report report) {
		
		UserAccount userAccount = LoginService.getPrincipal();
		Actor actor = actorService.findByPrincipal();
		if(actor instanceof Customer) {
			Assert.isTrue(complaintService.findCustomerComplaints().contains(report.getComplaint()));
		} else if(actor instanceof Worker) {
			Assert.isTrue(complaintService.findWorkerComplaints().contains(report.getComplaint()));
		} else if(actor instanceof Referee) {
			Assert.isTrue(report.getReferee().getUserAccount().equals(userAccount));
		} else {
			Assert.isTrue(false);
		}
		
		Assert.isTrue(report.isFinalReport());
		
		Note result;
		
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		
		result = new Note();

		result.setMoment(moment);

		return result;
	}

	public Note save(Note note, Report report) {

		UserAccount userAccount = LoginService.getPrincipal();
		Actor actor = actorService.findByPrincipal();
		if(actor instanceof Customer) {
			Assert.isTrue(complaintService.findCustomerComplaints().contains(report.getComplaint()));
		} else if(actor instanceof Worker) {
			Assert.isTrue(complaintService.findWorkerComplaints().contains(report.getComplaint()));
		} else if(actor instanceof Referee) {
			Assert.isTrue(report.getReferee().getUserAccount().equals(userAccount));
		} else {
			Assert.isTrue(false);
		}
		
		Assert.isTrue(report.isFinalReport());

		Assert.notNull(note);
		Note result;
		
		result = noteRepository.save(note);

		report.getNotes().add(result);
		reportService.save(report);

		return result;
	}

	public void delete(Note note, Report report) {

		UserAccount userAccount = LoginService.getPrincipal();
		Actor actor = actorService.findByPrincipal();
		if(actor instanceof Customer) {
			Assert.isTrue(complaintService.findCustomerComplaints().contains(report.getComplaint()));
		} else if(actor instanceof Worker) {
			Assert.isTrue(complaintService.findWorkerComplaints().contains(report.getComplaint()));
		} else if(actor instanceof Referee) {
			Assert.isTrue(report.getReferee().getUserAccount().equals(userAccount));
		} else {
			Assert.isTrue(false);
		}
		
		Assert.isTrue(report.isFinalReport());

		Assert.notNull(note);
		Assert.isTrue(note.getId() != 0);
		Assert.isTrue(noteRepository.exists(note.getId()));

		noteRepository.delete(note);
	}

	// Other business methods -------------------------------------------------

	public Note writeComment(Note note, Report report, String comment) {
		
		UserAccount userAccount = LoginService.getPrincipal();
		Actor actor = actorService.findByPrincipal();
		if(actor instanceof Customer) {
			Assert.isTrue(complaintService.findCustomerComplaints().contains(report.getComplaint()));
			note.setCustomerComment(comment);
		} else if(actor instanceof Worker) {
			Assert.isTrue(complaintService.findWorkerComplaints().contains(report.getComplaint()));
			note.setWorkerComment(comment);
		} else if(actor instanceof Referee) {
			Assert.isTrue(report.getReferee().getUserAccount().equals(userAccount));
			note.setRefereeComment(comment);
		} else {
			Assert.isTrue(false);
		}
		
		Note result;
		
		result = noteRepository.save(note);

		return result;
	}
	
	public Double[] getNotesPerReportStatistics() {

		Double[] result = noteRepository.getNotesPerReportStatistics();

		return result;

	}

}
