  package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

	@Query("select a.sentMessages from Actor a where a.id=?1")
	public Collection<Message> getSentMessagesById(int actorId);
	
	@Query("select a.receivedMessages from Actor a where a.id=?1")
	public Collection<Message> getReceivedMessagesById(int actorId);
	
	@Query("select b.messages from Box b where b.id=?1")
	public Collection<Message> getMessagesByBoxId(int boxId);
	
	
	
}