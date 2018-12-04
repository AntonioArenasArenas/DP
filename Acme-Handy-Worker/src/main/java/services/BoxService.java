package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BoxRepository;
import domain.Actor;
import domain.Box;
import domain.Message;

@Service
@Transactional
public class BoxService {
	// Managed repository -----------------------------------------------------

	@Autowired
	private BoxRepository boxRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public BoxService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	// ---
	public Box create() {
		Box result;
		Collection<Message> messages = new ArrayList<>();
		result = new Box();

		result.setMessages(messages);

		result.setIsDefault(false);

		return result;
	}

	public Collection<Box> createDefault() {
		Collection<Box> result;
		Box box;

		result = new ArrayList<Box>();

		box = new Box();
		box.setName("TRASHBOX");
		box.setIsDefault(true);
		result.add(box);
		box = new Box();
		box.setName("INBOX");
		box.setIsDefault(true);
		result.add(box);
		box = new Box();
		box.setName("OUTBOX");
		box.setIsDefault(true);
		result.add(box);
		box = new Box();
		box.setName("SPAMBOX");
		box.setIsDefault(true);
		result.add(box);

		return result;
	}

	public Collection<Box> findAll() {
		Collection<Box> result;

		result = boxRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Box findOne(int boxId) {
		Assert.isTrue(boxId != 0);

		Box result;

		result = boxRepository.findOne(boxId);
		Assert.notNull(result);

		return result;
	}

	public Box save(Box box) {

		Assert.notNull(box);

		Box result;

		result = boxRepository.save(box);

		return result;
	}

	public void delete(Box box) {

		Actor actor;

		Assert.notNull(box);
		Assert.isTrue(box.getId() != 0);

		actor = actorService.findByPrincipal();
		Assert.isTrue(actor.getBoxes().contains(box));
		Assert.isTrue(!box.getIsDefault());

		Assert.isTrue(actor.getBoxes().size() > 1);

		boxRepository.delete(box);
	}

	public Collection<Box> findBoxesWhithMessage(Message message) {

		return boxRepository.getBoxesWithMessage(message.getId());

	}

	public Box findBoxByActor(String boxName, int actorId) {
		Box result;

		result = this.boxRepository.findBoxByActor(boxName, actorId);
		Assert.notNull(result);

		return result;

	}
}
