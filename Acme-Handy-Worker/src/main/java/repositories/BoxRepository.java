package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Box;

@Repository
public interface BoxRepository extends JpaRepository<Box, Integer>{


	
	@Query("select b from Actor a join a.boxes b where b.name=?1 and a.id=?2")
	public Box findBoxByActor(String boxName ,int actorId);
	
	@Query("select b from Box b where ?1 member of b.messages")
	public Collection<Box> getBoxesWithMessage(int MessageId);
	

}
