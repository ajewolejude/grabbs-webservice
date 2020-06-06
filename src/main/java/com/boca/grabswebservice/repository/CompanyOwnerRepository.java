package com.boca.grabswebservice.repository;

import com.boca.grabswebservice.model.CompanyOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CompanyOwnerRepository extends JpaRepository<CompanyOwner, Long> {
    @Modifying
    @Query("update CompanyOwner c set c.deleted_by = :deleted_by , c.deleted_at= :deleted_at  where c.id= :id ")
    void softDelete(@Param("deleted_by") Integer deleted_by, @Param("deleted_at") Date deleted_at, @Param("id") long id);

    @Modifying
    @Query("update CompanyOwner c set c.status = :status where c.id= :id ")
    void updateStatus(@Param("status") String status, @Param("id") long id);

    @Query(value = "select c.email from companyowner c where c.email= :email limit 1",
            nativeQuery=true)
    String getExistingEmail(@Param("email") String email);

    @Query(value = "select c.email from companyowner c where c.contact_email= :email limit 1",
            nativeQuery=true)
    String getExistingContactEmail(@Param("email") String email);

    @Query(value = "select c.email from companyowner c where c.rc_no= :rc_no limit 1",
            nativeQuery=true)
    String getExistingRC(@Param("rc_no") String rc_no);

    @Query(value = "select * from companyowner c where c.status = :status ORDER BY d.id DESC", nativeQuery = true)
    List<CompanyOwner> getByStatus(@Param("status") String status);

    @Query(value = "select * from companyowner c where c.email = :email", nativeQuery = true)
    CompanyOwner getByEmail(String email);

    @Query(value = "select * from user u inner join companyowner c on c.email = u.email where u.id = :id", nativeQuery = true)
    CompanyOwner getAllUserDetails(Long id);

    @Query(value = "select * from companyowner c inner join user u  on c.user_id = u.id where u.email = :email", nativeQuery = true)
    CompanyOwner getAllUserDetailsByEmail(String email);

    @Query(value = "select * from companyowner c inner join  user u on c.user_id = u.id where c.user_id = :id", nativeQuery = true)
    CompanyOwner getUserProfile(Long id);

    @Query(value = "select * from companyowner c inner join  user u on c.email = u.email where u.email = :email", nativeQuery = true)
    CompanyOwner getUserProfileByEmail(String email);

    @Query(value = "select * from companyowner c inner join  user u on c.user_id = u.id ORDER BY c.id DESC", nativeQuery = true)
    List<CompanyOwner> getAllCompanyOwners();


    @Query(value = "select * from companyowner c where c.user_id = :id", nativeQuery = true)
    CompanyOwner getOneCompanyOwner(@Param("id") long id);
}

