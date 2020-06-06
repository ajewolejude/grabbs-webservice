package com.boca.grabswebservice.repository;

import com.boca.grabswebservice.model.Mate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MateRepository extends JpaRepository<Mate, Long> {

    @Modifying
    @Query("update Mate d set d.deleted_by = :deleted_by , d.deleted_at= :deleted_at  where d.id= :id ")
    void softDelete(@Param("deleted_by") Integer deleted_by, @Param("deleted_at") Date deleted_at, @Param("id") long id);

    @Modifying
    @Query("update Mate d set d.status = :status where d.id= :id ")
    void updateStatus(@Param("status") String status, @Param("id") long id);

    @Query(value = "select d.license_no from mate d where d.license_no= :license_no limit 1",
            nativeQuery = true)
    String getExistingLicenseNo(@Param("license_no") String license_no);

    @Query(value = "select d.email from mate d where d.email= :email limit 1",
            nativeQuery = true)
    String getExistingEmail(@Param("email") String email);

    @Query(value = "select * from mate m where m.status = :status and m.deleted_by = 0", nativeQuery = true)
    List<Mate> getAllByStatus(@Param("status") String status);

    @Query(value = "select * from mate m where m.email = :email", nativeQuery = true)
    Mate getByEmail(String email);

    @Query(value = "select * from user u inner join mate m on m.email = u.email where u.id = :id", nativeQuery = true)
    Mate getAllUserDetails(Long id);

    @Query(value = "select * from user u inner join mate m on m.user_id = u.id where m.user_id = :id", nativeQuery = true)
    Mate getUserProfile(Long id);


    @Query(value = "select * from user u inner join mate m on m.user_id = u.id where u.email = :email`", nativeQuery = true)
    Mate getUserProfileByEmail(String email);

    @Query(value = "select * from mate m where m.deleted_by = 0 ORDER BY m.id DESC", nativeQuery = true)
    List<Mate> findAllByOrderByIdDesc();

}
