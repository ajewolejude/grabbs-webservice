package com.boca.grabswebservice.repository;

import com.boca.grabswebservice.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {

    @Modifying
    @Query("update Merchant m set m.deleted_by = :deleted_by , m.deleted_at= :deleted_at  where m.id= :id ")
    void softDelete(@Param("deleted_by") Integer deleted_by, @Param("deleted_at") Date deleted_at, @Param("id") long id);

    @Modifying
    @Query("update Merchant m set m.status = :status where m.id= :id ")
    void updateStatus(@Param("status") String status, @Param("id") long id);


    @Query(value = "select m.email from merchant m where m.email= :email limit 1",
            nativeQuery = true)
    String getExistingEmail(@Param("email") String email);


    @Query(value = "select * from user u inner join merchant m on m.user_id = u.id where m.user_id = :id", nativeQuery = true)
    Merchant getUserProfile(Long id);

    @Query(value = "select * from merchant m inner join user u on m.email = u.email where u.email =:email", nativeQuery = true)
    Merchant getUserProfileByEmail(String email);

    @Query(value = "select * from merchant m where m.deleted_by = 0 ORDER BY m.id DESC", nativeQuery = true)
    List<Merchant> findAllByOrderByIdDesc();

}
