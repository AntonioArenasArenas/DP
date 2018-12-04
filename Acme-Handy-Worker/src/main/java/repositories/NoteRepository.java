package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer>{
	
	@Query("select avg(r.notes.size), min(r.notes.size), max(r.notes.size), stddev(r.notes.size) from Report r")
	public Double[] getNotesPerReportStatistics();
	
}
