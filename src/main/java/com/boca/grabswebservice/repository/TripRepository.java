package com.boca.grabswebservice.repository;

import com.boca.grabswebservice.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    @Query(value = "select * from trip t where t.status =:status ORDER BY t.id DESC", nativeQuery = true)
    List<Trip> getAllByStatus(String status);

    @Query(value = "select * from trip t where t.driver_id =:id and t.status =:status ORDER BY t.id DESC", nativeQuery = true)
    List<Trip> getAllByStatusAndDriver(Long id, String status);


    @Query(value = "select * from trip t where t.driver_id =:id ORDER BY t.id DESC", nativeQuery = true)
    List<Trip> getAllByDriver(Long id);


    @Query(value = "select * from trip t where t.deleted_by = 0 ORDER BY t.id DESC", nativeQuery = true)
    List<Trip> findAllByOrderByIdDesc();

    @Query(value = "SELECT SUM(CASE WHEN status =:status THEN charge ELSE 0 END) FROM trip ", nativeQuery = true)
    BigDecimal selectTotalTripChargeByStatus(@Param("status") String status);

    @Query(value = "SELECT SUM(CASE WHEN status ='End'THEN charge ELSE 0 END) FROM trip where status ='End' ", nativeQuery = true)
    BigDecimal selectTotalTripCharge();

    @Query(value = "SELECT SUM(CASE  WHEN status ='End' and truck_id=:truck_id THEN charge ELSE 0 END) FROM trip ", nativeQuery = true)
    BigDecimal selectTotalTripCharge(Long truck_id);

    @Query(value = "SELECT SUM(CASE  WHEN status ='End' and truck_id in :truck_id_list THEN charge ELSE 0 END) FROM trip ", nativeQuery = true)
    BigDecimal selectTotalTripCharge(Long[] truck_id_list);

    @Query(value = "select * from trip t where t.deleted_by = 0 and t.truck_id =:truck_id and t.status ='End' ORDER BY t.id DESC", nativeQuery = true)
    List<Trip> getAllTripChargeForTruck(@Param("truck_id") Long truck_id);

    @Query(value = "SELECT SUM(CASE WHEN status ='End' and MONTH(date_of_reg)=:month and YEAR(date_of_reg)=:year THEN charge ELSE 0 END ) FROM trip ", nativeQuery = true)
    BigDecimal selectTotalTripChargeThisMonth(int month, int year);

    @Query(value = "SELECT SUM(CASE WHEN status ='End' and truck_id in :truck_id_list and MONTH(date_of_reg)=:month and YEAR(date_of_reg)=:year THEN charge ELSE 0 END ) FROM trip ", nativeQuery = true)
    BigDecimal selectTotalTripChargeThisMonth(Long[] truck_id_list, int month, int year);

    @Query(value = "SELECT sum(CASE WHEN p.type ='End' and t.truck_id =:truck_id and MONTH(p.date_of_reg)=:month and YEAR(p.date_of_reg)=:year THEN charge ELSE 0 END ) FROM trip t  inner join process p on p.trip_id = t.id", nativeQuery = true)
    BigDecimal selectTotalTripChargeThisMonthForTruck(Long truck_id, int month, int year);

//    @Query(value =" SELECT SUM(CASE  WHEN status !='Canceled' and MONTH(date_of_reg)=:month and YEAR(date_of_reg)=:year THEN charge ELSE 0 END) as sumMonth, SUM(charge) as sumTotal  FROM trip as Sum where status !='Canceled'", nativeQuery = true)
//    Sum selectTotalTripChargeThisMonthAndTotal(int month, int year);
}