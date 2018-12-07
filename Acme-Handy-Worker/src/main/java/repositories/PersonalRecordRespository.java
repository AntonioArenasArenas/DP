package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.PersonalRecord;

public interface PersonalRecordRespository extends JpaRepository<PersonalRecord, Integer>{

}
