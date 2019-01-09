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
		Collection<Box> result = new ArrayList<Box>();

		Box box1 = new Box();
		box1.setName("TRASHBOX");
		box1.setMessages(new ArrayList<Message>());
		box1.setIsDefault(true);
		Box box1saved = boxRepository.save(box1);
		result.add(box1saved);
		
		Box box2 = new Box();
		box2.setName("INBOX");
		box2.setMessages(new ArrayList<Message>());
		box2.setIsDefault(true);
		Box box2saved = boxRepository.save(box2);
		result.add(box2saved);
		
		Box box3 = new Box();
		box3.setName("OUTBOX");
		box3.setMessages(new ArrayList<Message>());
		box3.setIsDefault(true);
		Box box3saved = boxRepository.save(box3);
		result.add(box3saved);
		
		Box box4 = new Box();
		box4.setName("SPAMBOX");
		box4.setMessages(new ArrayList<Message>());
		box4.setIsDefault(true);
		Box box4saved = boxRepository.save(box4);
		result.add(box4saved);
		
		
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
		
		Actor actor = actorService.findByPrincipal();
		
		Box result;

		result = boxRepository.save(box);
		
		if(box.getId()==0){
		Collection<Box> boxes = actor.getBoxes();
		boxes.add(result);
		actor.setBoxes(boxes);
		}
		return result;
	}

	public void delete(Box box) {

		Actor actor;

		Assert.notNull(box);
		Assert.isTrue(box.getId() != 0);

		actor = actorService.findByPrincipal();
		Assert.isTrue(actor.getBoxes().contains(box));
		Assert.isTrue(!box.getIsDefault());
		Assert.isTrue(actor.getBoxes().size() > 1); // >= 4
		
		box.getMessages().clear();
		actor.getBoxes().remove(box);

		boxRepository.delete(box);
	}


	public Collection<Box> findBoxesWithMessage(Message message) {


		return boxRepository.getBoxesWithMessage(message.getId());

	}

	public Box findBoxByActor(String boxName, int actorId) {
		Box result;

		result = this.boxRepository.findBoxByActor(boxName, actorId);
		Assert.notNull(result);

		return result;

	}
	
	public Collection<Box> findBoxesByPrincipal() {
		Collection<Box> result;
		Actor logged = actorService.findByPrincipal();

		result = this.boxRepository.getBoxesByPrincipal(logged.getId());
		Assert.notNull(result);

		return result;

	}
}
