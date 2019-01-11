package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SystemData;

@Repository
public interface SystemDataRepository extends JpaRepository<SystemData, Integer>{



}
