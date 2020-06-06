package com.boca.grabswebservice.repository;

import com.boca.grabswebservice.model.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long> {

    @Modifying
    @Query("update Truck t set t.deleted_by = :deleted_by  where t.id= :id ")
    void softDelete(@Param("deleted_by") Integer deleted_by, @Param("id") long id);

    @Query(value = "select t.license_no from truck t where t.license_no= :license_no limit 1",
            nativeQuery = true)
    String getExistingLicenseNo(@Param("license_no") String license_no);

    @Query(value = "select * from truck t where t.owner_id= :owner_id and t.owner_role =:owner_role and t.deleted_by = 0 ORDER BY t.id DESC",
            nativeQuery = true)
    List<Truck> getMyTrucks(@Param("owner_id") Long owner_id, @Param("owner_role") String owner_role);

    @Modifying
    @Query("update Truck t set t.status = :status where t.id= :id ")
    void updateStatus(@Param("status") String status, @Param("id") long id);

    @Modifying
    @Query("update Truck t set t.trip_status = :trip_status where t.id= :id ")
    void updateTripStatus(@Param("trip_status") String trip_status, @Param("id") long id);


    @Query(value = "select * from truck t where t.trip_status = 'idle' and t.status= 'active' and t.deleted_by = 0 ORDER BY t.id DESC", nativeQuery = true)
    List<Truck> getAllIdleActiveTrucks();

    @Query(value = "select * from truck t where t.deleted_by = 0 ORDER BY t.id DESC", nativeQuery = true)
    List<Truck> findAllByOrderByIdDesc();
}
