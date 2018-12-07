package services;

import java.util.Collection;
import java.util.Date;

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

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

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

		Message result;

		result = messageRepository.findOne(MessageId);
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
		Date moment;
		Actor actor = actorService.findByPrincipal();
		Message result = message;
		Box outBox = boxService.findBoxByActor("OUTBOX", actor.getId());
		Collection<Actor> recipients = result.getRecipients();
		moment = new Date(System.currentTimeMillis() - 1);

		Assert.notNull(message);
		userAccount = LoginService.getPrincipal();
		Assert.isTrue(message.getSender().getUserAccount().equals(userAccount));

		result.setMoment(moment);

		result = messageRepository.save(message);

		actor.getSentMessages().add(result);
		outBox.getMessages().add(result);
		boxService.save(outBox);
		actorService.save(actor);

		for (Actor a : recipients) {

			Box InBox = boxService.findBoxByActor("INBOX", a.getId());
			InBox.getMessages().add(result);
			a.getReceivedMessages().add(result);
			boxService.save(InBox);
			actorService.save(a);

		}

		return result;
	}

	public void delete(Message message, Box box) {
		Assert.notNull(message);
		Assert.isTrue(message.getId() != 0);
		Assert.isTrue(messageRepository.exists(message.getId()));

		UserAccount userAccount;
		Actor logged;
		// owner of the box
		userAccount = LoginService.getPrincipal();
		logged = actorService.findByUserAccount(userAccount);
		Assert.isTrue(message.getRecipients().contains(logged)
				|| message.getSender().equals(logged));
		Box TrashBox = boxService.findBoxByActor("TRASHBOX", logged.getId());
		box.getMessages().remove(message);

		if (box.getName() == TrashBox.getName()) {

			if (boxService.findBoxesWhithMessage(message).isEmpty()) {

				messageRepository.delete(message);
			}

		} else {

			TrashBox.getMessages().add(message);
			boxService.save(TrashBox);

		}

		boxService.save(box);

	}

	public Collection<Message> findSentMessagesById() {
		UserAccount userAccount = LoginService.getPrincipal();
		Actor logged = actorService.findByUserAccount(userAccount);

		return messageRepository.getSentMessagesById(logged.getId());

	}

	public Collection<Message> findRecievedMessagesById() {
		UserAccount userAccount = LoginService.getPrincipal();
		Actor logged = actorService.findByUserAccount(userAccount);

		return messageRepository.getRecievedMessagesById(logged.getId());
	}

}
