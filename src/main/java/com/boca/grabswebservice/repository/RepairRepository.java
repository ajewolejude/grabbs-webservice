package com.boca.grabswebservice.repository;

import com.boca.grabswebservice.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {

    @Modifying
    @Query("update Repair r set r.deleted_by = :deleted_by , r.deleted_at= :deleted_at  where r.id= :id ")
    void softDelete(@Param("deleted_by") Integer deleted_by, @Param("deleted_at") Date deleted_at, @Param("id") long id);

    @Query(value = "select * from repair r where r.jobcard_id= :jobcard_id and r.deleted_by = 0 ORDER BY r.id DESC",
            nativeQuery = true)
    List<Repair> getAllRepairsByJobCard(@Param("jobcard_id") Long jobcard_id);

}
