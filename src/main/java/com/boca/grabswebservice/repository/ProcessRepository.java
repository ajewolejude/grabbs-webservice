package com.boca.grabswebservice.repository;

import com.boca.grabswebservice.model.Process;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessRepository extends JpaRepository<Process, Long> {



    @Query(value = "select * from process p inner join trip t on t.status = p.type where t.id = p.trip_id  and t.id = :id", nativeQuery = true)
    Process getRecentProcessByTripId(Long id);

    @Query(value = "select * from process p inner join trip t on t.id = p.trip_id where t.id=:id", nativeQuery = true)
    List<Process> getAllProcessesByTripId(Long id);
}