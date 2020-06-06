package com.boca.grabswebservice.repository;

import com.boca.grabswebservice.model.JobCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface JobCardRepository extends JpaRepository<JobCard, Long> {

    @Query(value = "select * from jobcard j where j.truck_id= :truck_id and j.deleted_by = 0 ORDER BY j.id DESC",
            nativeQuery = true)
    List<JobCard> getAllJobCardsByTruckId(@Param("truck_id") Long truck_id);


    @Modifying
    @Query("update JobCard j set j.deleted_by = :deleted_by , j.deleted_at= :deleted_at  where j.id= :id ")
    void softDelete(@Param("deleted_by") Integer deleted_by, @Param("deleted_at") Date deleted_at, @Param("id") long id);

    @Query(value = "SELECT SUM(CASE WHEN j.deleted_by = 0 and MONTH(date_of_reg)=:month and YEAR(date_of_reg)=:year THEN j.workmanship ELSE 0 END)  FROM jobcard j  ", nativeQuery = true)
    BigDecimal selectTotalMaintenanceThisMonth(int month, int year);

    @Query(value = "SELECT SUM(CASE WHEN j.deleted_by = 0  and j.truck_id=:truck_id and MONTH(date_of_reg)=:month and YEAR(date_of_reg)=:year THEN j.workmanship ELSE 0 END)  FROM jobcard j  ", nativeQuery = true)
    BigDecimal selectTotalMaintenanceThisMonth(Long truck_id, int month, int year);

    @Query(value = "SELECT SUM(CASE WHEN j.deleted_by = 0    and j.truck_id in :truck_id_list and MONTH(date_of_reg)=:month and YEAR(date_of_reg)=:year THEN j.workmanship ELSE 0 END)  FROM jobcard j  ", nativeQuery = true)
    BigDecimal selectTotalMaintenanceThisMonth(Long[] truck_id_list, int month, int year);

    @Query(value = "SELECT SUM(CASE WHEN j.deleted_by = 0 THEN j.workmanship ELSE 0 END)  FROM jobcard j ", nativeQuery = true)
    BigDecimal selectTotalMaintenance();

    @Query(value = "SELECT SUM(CASE WHEN j.deleted_by = 0 and j.truck_id=:truck_id THEN j.workmanship ELSE 0 END)  FROM jobcard j ", nativeQuery = true)
    BigDecimal selectTotalMaintenance(Long truck_id);

    @Query(value = "SELECT SUM(CASE WHEN j.deleted_by = 0 and j.truck_id in :truck_id_list THEN j.workmanship ELSE 0 END)  FROM jobcard j ", nativeQuery = true)
    BigDecimal selectTotalMaintenance(Long[] truck_id_list);

}
