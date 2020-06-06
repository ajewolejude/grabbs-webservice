package com.boca.grabswebservice.repository;

import com.boca.grabswebservice.model.Tyre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TyreRepository extends JpaRepository<Tyre, Long> {

    @Query(value = "select * from tyre t where t.truck_id= :truck_id",
            nativeQuery=true)
    List<Tyre> getTyres(@Param("truck_id") Long truck_id);

    @Query(value = "select * from tyre t where t.status= :status",
            nativeQuery=true)
    List<Tyre> getTyresByStatus(@Param("status") String status);

    @Query(value = "select * from tyre tyr left join truck trc on trc.id = tyr.truck_id", nativeQuery = true)
    List<Tyre> getTyresWithTruck();

    @Query(value = "select t.serial_number from tyre t where t.serial_number= :license_no limit 1",
            nativeQuery=true)
    String getExistingSerialNo(@Param("license_no") String serial_number);



    @Query(value = "select * from Tyre t ORDER BY t.id DESC", nativeQuery = true)
    List<Tyre> findAllByOrderByIdDesc();

}
