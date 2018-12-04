package services;

import java.util.Collection;
import java.util.LinkedList;

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
import domain.Message;
import domain.Worker;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BoxServiceTest extends AbstractTest {

	// Supporting services ----------------------------------------------------
	@Autowired
	private BoxService boxService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private WorkerService workerService;

	@Autowired
	private ActorService actorService;

	// Test ----------------------------------------------------

	@Test
	public void testCreateDefaultBox() {
		Collection<Box> defaults;

		defaults = boxService.createDefault();
		Assert.isTrue(!defaults.isEmpty());
		Assert.isTrue(defaults.size() == 4);
	}

	@Test
	public void testCreateBox() {
		super.authenticate("superman");

		Box box = boxService.create();
		box.setName("TRABAJO");
		Box persisted = boxService.save(box);

		Collection<Box> boxes = boxService.findAll();
		super.authenticate(null);

		Assert.isTrue(boxes.contains(persisted));

	}

	@Test
	public void testDeleteBox() {
		super.authenticate("supertroll");
		Worker w = workerService.findByPrincipal();
		Collection<Box> boxes = w.getBoxes();
		LinkedList<Box> boxeslist = new LinkedList<Box>(boxes);

		if (boxes.size() > 2) {

			Box box = boxeslist.getFirst();

			w.getBoxes().remove(box);
			workerService.save(w);

			boxService.delete(box);

			Collection<Box> boxessaved = boxService.findAll();
			Assert.isTrue(!boxessaved.contains(box));
		}

		// No se debe borrar la caja si es la única que hay en ese momento en el
		// actor

	}

	@Test
	public void testFindBoxByActor() {

		Collection<Actor> actors = actorService.findAll();
		LinkedList<Actor> actorslist = new LinkedList<Actor>(actors);
		Actor actor = actorslist.getFirst();
		Collection<Box> boxes = actor.getBoxes();
		LinkedList<Box> boxeslist = new LinkedList<Box>(boxes);
		Box box = boxeslist.getFirst();
		Box result = boxService.findBoxByActor(box.getName(), actor.getId());

		Assert.notNull(result);

	}

	@Test
	public void testFindBoxesWhithMessage() {

		Collection<Message> messages = messageService.findAll();
		LinkedList<Message> messageslist = new LinkedList<Message>(messages);
		Message message = messageslist.getFirst();

		// En este momento cualquier mensaje estará en dos carpetas así
		// que cualquier mensaje vale para estas pruebas

		Collection<Box> containers = boxService.findBoxesWhithMessage(message);

		Assert.isTrue(containers.size() == 2);
	}

}
