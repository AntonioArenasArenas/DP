package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Actor;
import domain.Box;
import domain.Customer;
import domain.Message;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class MessageServiceTest extends AbstractTest {

	// Supporting services ----------------------------------------------------
	@Autowired
	private MessageService messageService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private BoxService boxService;

	// Test ----------------------------------------------------


	@Test
	public void testCreateMessage(){
		super.authenticate("superman");
		Message message = messageService.createMessage();
		Collection<Actor> recipients = new ArrayList<Actor>();
		recipients.add(actorService.findOne(433));
		message.setRecipients(recipients);
		message.setSubject("subjetc");
		message.setBody("Body");
		message.setTags("#tag");
		message.setPriority("HIGH");
		Message saved = messageService.save(message);

		Assert.isTrue(messageService.findAll().contains(saved));

		Box Inbox = boxService.findOne(406);
		Box Outbox = boxService.findOne(412);

		Assert.isTrue(Inbox.getMessages().contains(saved));
		Assert.isTrue(Outbox.getMessages().contains(saved));

	}

	@Test
	public void testFindSentMessagesById() {
		// el actor superman ha enviado mensajes
		super.authenticate("superman");
		Collection<Message> result = messageService.findSentMessagesById();
		super.unauthenticate();

		Assert.notEmpty(result);

	}

	@Test
	public void testFindReceivedMessagesById() {
		// El actor superguay ha recibido mensajes
		super.authenticate("superman");
		Collection<Message> result = messageService.findReceivedMessagesById();
		super.unauthenticate();

		Assert.notEmpty(result);

	}

	@Test
	public void testDelete() {
		super.authenticate("superman");
		Box box = boxService.findBoxByActor("OUTBOX", 432);
		Box trashbox = boxService.findBoxByActor("TRASHBOX", 432);

		Message message = messageService.findOne(456);
		this.messageService.delete(message, box);

		Assert.isTrue(trashbox.getMessages().contains(message));
		Assert.isTrue(!box.getMessages().contains(message));
		super.unauthenticate();

	}

	@Test
	public void testDelete2() {
		super.authenticate("superman");
		Box trashbox = boxService.findBoxByActor("TRASHBOX", 432);

		Message message = messageService.findOne(457);
		Assert.isTrue(trashbox.getMessages().contains(message));
		this.messageService.delete(message, trashbox);


		Assert.isTrue(trashbox.getMessages().isEmpty());
		super.unauthenticate();

	}

}
