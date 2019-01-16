package services;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Box;
import domain.Message;
import domain.SystemData;
import domain.Task;
import domain.Worker;

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

	//Supported services
	@Autowired
	private SystemDataService systemDataService;

	// Constructors -----------------------------------------------------------

	public MessageService() {
		super();
	}

	@Autowired
	private BoxService boxService;

	@Autowired
	private ActorService actorService;

	// Simple CRUD methods ----------------------------------------------------

	public Collection<Message> findAll() {
		Collection<Message> result;

		result = messageRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Message findOne(int MessageId) {
		Assert.isTrue(MessageId != 0);
		Actor logged = actorService.findByPrincipal();
		Message result;

		result = messageRepository.findOne(MessageId);

		Assert.isTrue(logged.getReceivedMessages().contains(result)||
				logged.getSentMessages().contains(result));
		Assert.notNull(result);

		return result;
	}

	public Message createMessage() {
		Message result;

		result = new Message();
		Actor actor;
		actor = actorService.findByPrincipal();
		result.setSender(actor);
		return result;
	}

	public Message save(Message message) {
		UserAccount userAccount;
		Date moment = new Date(System.currentTimeMillis() - 1);
		Actor actor = actorService.findByPrincipal();
		Message result = message;
		Box outBox = boxService.findBoxByActor("OUTBOX", actor.getId());
		Collection<Actor> recipients = result.getRecipients();
		Assert.notNull(message);
		userAccount = LoginService.getPrincipal();
		Assert.isTrue(message.getSender().getUserAccount().equals(userAccount));
		SystemData data = (SystemData) systemDataService.findAll().toArray()[0];
		Collection<String> spamwords = data.getSpamWords();

		result.setMoment(moment);

		result = messageRepository.save(message);

		for(String word : spamwords){

			if(message.getBody().contains(word)||message.getTags().contains(word)
					||message.getSubject().contains(word)){

				actor.setSuspicious(true);

				for( Actor a : recipients){

					Box InBox = boxService.findBoxByActor("SPAMBOX", a.getId());
					InBox.getMessages().add(result);
					a.getReceivedMessages().add(result);
					boxService.save(InBox);
					actorService.save(a);
					break;
					}

			  }else{
				  for( Actor a : recipients){

					Box InBox = boxService.findBoxByActor("INBOX", a.getId());
					InBox.getMessages().add(result);
					a.getReceivedMessages().add(result);
					boxService.save(InBox);
					actorService.save(a);
					break;
				}
			  }
		break;
		}



		actor.getSentMessages().add(result);
		outBox.getMessages().add(result);
		boxService.save(outBox);
		actorService.save(actor);




		return result;
	}

	public void delete(Message message) {
		Assert.notNull(message);
		Assert.isTrue(message.getId() != 0);
		Assert.isTrue(messageRepository.exists(message.getId()));

		UserAccount userAccount;
		Actor logged;
		userAccount = LoginService.getPrincipal();
		logged = actorService.findByUserAccount(userAccount);
		Assert.isTrue(logged.getReceivedMessages().contains(message)||
				logged.getSentMessages().contains(message));
		Box TrashBox = boxService.findBoxByActor("TRASHBOX", logged.getId());

		if (TrashBox.getMessages().contains(message)) {

			Collection<Actor> actors = actorService.findAll();
			;
			Collection<Box> all = boxService.findAll();

			for (Box b : all) {
				if (b.getMessages().contains(message)) {
					b.getMessages().remove(message);
					boxService.save(b);
				}
			}

			for (Actor a : actors) {
				if (a.getSentMessages().contains(message)) {
					a.getSentMessages().remove(message);
					actorService.save(a);
				}
				if (a.getReceivedMessages().contains(message)) {
					a.getReceivedMessages().remove(message);
					actorService.save(a);
				}
			}

			messageRepository.delete(message);

		} else {
			Collection<Box> loggedboxes = boxService.findBoxesByPrincipal();
			for (Box b : loggedboxes) {
				if (b.getMessages().contains(message)) {
					b.getMessages().remove(message);
					boxService.save(b);
					TrashBox.getMessages().add(message);
					boxService.save(TrashBox);
				}
			}
		}

	}

	public void applicationMessage(Task task, Worker worker) {
		Assert.notNull(task);
		Assert.notNull(worker);
		Actor logged = actorService.findByPrincipal();
		Assert.notNull(logged);
		Collection<Actor> recipients = new LinkedList<Actor>();
		recipients.add(worker);
		recipients.add(logged);
		Assert.notNull(logged);

		Message message = this.createMessage();
		message.setBody("Your application  of task:" + task.getTicker()
				+ "has been updated! " + " Tu aplicaci�n de la tarea"
				+ task.getTicker() + " ha sido actualizada!");
		message.setPriority("HIGH");
		message.setTags("Application");
		message.setSubject("Application updated");
		message.setSender(logged);
		message.setRecipients(recipients);
		Message saved = this.save(message);
		Assert.notNull(saved);

	}

	public Collection<Message> findSentMessagesById() {
		UserAccount userAccount = LoginService.getPrincipal();
		Actor logged = actorService.findByUserAccount(userAccount);

		return messageRepository.getSentMessagesById(logged.getId());

	}

	public Collection<Message> findReceivedMessagesById() {
		UserAccount userAccount = LoginService.getPrincipal();
		Actor logged = actorService.findByUserAccount(userAccount);

		return messageRepository.getReceivedMessagesById(logged.getId());
	}

	public Collection<Message> findMessagesByBoxId(Integer boxId) {

		return messageRepository.getMessagesByBoxId(boxId);
	}

}
