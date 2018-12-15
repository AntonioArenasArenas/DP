package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import domain.Finder;


public interface FinderRepository extends JpaRepository<Finder, Integer>{

    @Query("select w.finder from Worker w where w.id=?1")
    public Finder getFinderByWorkerId(int workerId);
}

