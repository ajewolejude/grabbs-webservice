package com.boca.grabswebservice.repository;

import com.boca.grabswebservice.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

    @Modifying
    @Query("update Part p set p.deleted_by = :deleted_by , p.deleted_at= :deleted_at  where p.id= :id ")
    void softDelete(@Param("deleted_by") Integer deleted_by, @Param("deleted_at") Date deleted_at, @Param("id") long id);

    @Query(value = "select * from part p where p.jobcard_id= :jobcard_id and p.deleted_by = 0 ORDER BY p.id DESC",
            nativeQuery = true)
    List<Part> getAllPartsByJobCard(@Param("jobcard_id") Long jobcard_id);

    @Query(value = "SELECT SUM(CASE WHEN jobcard_id= :jobcard_id and deleted_by = 0 THEN amount ELSE 0 END) FROM part", nativeQuery = true)
    BigDecimal selectTotalAmount(@Param("jobcard_id") Long jobcard_id);

    @Query(value = "SELECT SUM(CASE WHEN deleted_by = 0 THEN amount ELSE 0 END) FROM part where deleted_by = 0", nativeQuery = true)
    BigDecimal selectTotalOverallAmount();

    @Query(value = "SELECT SUM(CASE WHEN p.deleted_by = 0 and j.truck_id=:truck_id THEN p.amount ELSE 0 END) FROM part p inner join jobcard j on j.id = p.jobcard_id", nativeQuery = true)
    BigDecimal selectTotalOverallAmount(Long truck_id);

    @Query(value = "SELECT SUM(CASE WHEN p.deleted_by = 0 and truck_id in :truck_id_list THEN p.amount ELSE 0 END) FROM part p inner join jobcard j on j.id = p.jobcard_id", nativeQuery = true)
    BigDecimal selectTotalOverallAmount(Long[] truck_id_list);

    @Query(value = "SELECT SUM(CASE WHEN deleted_by = 0 and MONTH(date_of_reg)=:month and YEAR(date_of_reg)=:year THEN amount ELSE 0 END) FROM part", nativeQuery = true)
    BigDecimal selectTotalOverallAmountThisMonth(int month, int year);

    @Query(value = "SELECT SUM(CASE WHEN p.deleted_by = 0  and j.truck_id in :truck_id_list and MONTH(p.date_of_reg)=:month and YEAR(p.date_of_reg)=:year THEN p.amount ELSE 0 END) FROM part p inner join jobcard j on j.id = p.jobcard_id", nativeQuery = true)
    BigDecimal selectTotalOverallAmountThisMonth(Long[] truck_id_list, int month, int year);

    @Query(value = "SELECT SUM(CASE WHEN p.deleted_by = 0 and j.truck_id=:truck_id and MONTH(p.date_of_reg)=:month and YEAR(p.date_of_reg)=:year THEN p.amount ELSE 0 END) FROM part p inner join jobcard j on j.id = p.jobcard_id", nativeQuery = true)
    BigDecimal selectTotalOverallAmountThisMonth(Long truck_id, int month, int year);


}
