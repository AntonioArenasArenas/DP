package services;

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
	private CustomerService customerService;
	@Autowired
	private BoxService boxService;

	// Test ----------------------------------------------------

	@Test
	public void testCreateMessage() {

		// Al no estar las beans completas nos easeguraremos de probarlo con un
		// usuario que tenga OUTBOX y el receptor INBOX

		Collection<Customer> customers = customerService.findAll();
		Iterator<Customer> it = customers.iterator();
		boolean exit = false;
		Customer c = new Customer();
		while (!exit && it.hasNext()) {
			c = it.next();
			Collection<Box> boxes = c.getBoxes();
			Iterator<Box> itb = boxes.iterator();
			boolean exitf = false;
			while (!exitf && itb.hasNext()) {
				Box b = itb.next();
				if (b.getName().equals("OUTBOX")) {
					exitf = true;
				}
			}
			if (exitf) {
				exit = true;
			}
		}
		Customer sender = c;
		exit = false;
		Customer recipient;
		while (!exit && it.hasNext()) {
			c = it.next();
			Collection<Box> boxes = c.getBoxes();
			Iterator<Box> itb = boxes.iterator();
			boolean exitf = false;
			while (!exitf && itb.hasNext()) {
				Box b = itb.next();
				if (b.getName().equals("INBOX")) {
					exitf = true;
				}
			}
			if (exitf) {
				exit = true;
			}
		}
		recipient = c;

		super.authenticate(sender.getUserAccount().getUsername());
		Message message = messageService.createMessage();
		message.setRecipient(recipient);
		message.setBody("cuerpo");
		message.setSubject("asunto");
		message.setPriority("HIGH");

		Message persisted = messageService.save(message);

		Collection<Message> messages = messageService.findAll();

		Assert.isTrue(messages.contains(persisted));
		super.authenticate(null);

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
		super.authenticate("superguay");
		Collection<Message> result = messageService.findReceivedMessagesById();
		super.unauthenticate();

		Assert.notEmpty(result);

	}

	@Test
	public void testDelete() {
		super.authenticate("superman");
		Box box = boxService.findBoxByActor("OUTBOX", 425);
		Box trashbox = boxService.findBoxByActor("TRASHBOX", 425);

		Message message = messageService.findOne(448);
		this.messageService.delete(message, box);

		Assert.isTrue(trashbox.getMessages().contains(message));
		Assert.isTrue(!box.getMessages().contains(message));
		super.unauthenticate();

	}

}
