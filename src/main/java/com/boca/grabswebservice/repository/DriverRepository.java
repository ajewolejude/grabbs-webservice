package com.boca.grabswebservice.repository;

import com.boca.grabswebservice.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    @Modifying
    @Query("update Driver d set d.deleted_by = :deleted_by , d.deleted_at= :deleted_at  where d.id= :id ")
    void softDelete(@Param("deleted_by") Integer deleted_by, @Param("deleted_at") Date deleted_at, @Param("id") long id);

    @Modifying
    @Query("update Driver d set d.status = :status where d.id= :id ")
    void updateStatus(@Param("status") String status, @Param("id") long id);


    @Query(value = "select d.license_no from driver d where d.license_no= :license_no limit 1",
            nativeQuery = true)
    String getExistingLicenseNo(@Param("license_no") String license_no);

    @Query(value = "select d.email from driver d where d.email= :email limit 1",
            nativeQuery = true)
    String getExistingEmail(@Param("email") String email);

    @Query(value = "select * from driver d where d.status = :status and d.deleted_by = 0", nativeQuery = true)
    List<Driver> getByStatus(@Param("status") String status);

    @Query(value = "select * from driver d where d.email = :email", nativeQuery = true)
    Driver getByEmail(String email);

    @Query(value = "select * from user u inner join driver d on d.email = u.email where u.id = :id", nativeQuery = true)
    Driver getAllUserDetails(Long id);

    @Query(value = "select * from user u inner join driver d on d.user_id = u.id where d.user_id = :id", nativeQuery = true)
    Driver getUserProfile(Long id);

    @Query(value = "select * from driver d  inner join user u on d.user_id = u.id where u.email = :email", nativeQuery = true)
    Driver getUserProfileByEmail(String email);

    @Query(value = "select * from driver d where d.deleted_by = 0 ORDER BY d.id DESC", nativeQuery = true)
    List<Driver> findAllByOrderByIdDesc();
}
