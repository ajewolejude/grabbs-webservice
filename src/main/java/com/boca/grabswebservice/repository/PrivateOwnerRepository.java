package com.boca.grabswebservice.repository;

import com.boca.grabswebservice.model.PrivateOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PrivateOwnerRepository extends JpaRepository<PrivateOwner, Long> {
    @Modifying
    @Query("update PrivateOwner p set p.deleted_by = :deleted_by , p.deleted_at= :deleted_at  where p.id= :id ")
    void softDelete(@Param("deleted_by") Integer deleted_by, @Param("deleted_at") Date deleted_at, @Param("id") long id);

    @Modifying
    @Query("update PrivateOwner p set p.status = :status where p.id= :id ")
    void updateStatus(@Param("status") String status, @Param("id") long id);

    @Query(value = "select p.email from privateowner p where p.email= :email limit 1",
            nativeQuery=true)
    String getExistingEmail(@Param("email") String email);

    @Query(value = "select * from privateowner p where p.status = :status ORDER BY p.id DESC", nativeQuery = true)
    List<PrivateOwner> getByStatus(@Param("status") String status);

    @Query(value = "select * from privateowner p where p.email = :email", nativeQuery = true)
    PrivateOwner getByEmail(String email);

    @Query(value = "select * from user u inner join privateowner p on p.email = u.email where u.id = :id", nativeQuery = true)
    PrivateOwner getAllUserDetails(Long id);



    @Query(value = "select * from user u inner join privateowner p on p.user_id = u.id where p.user_id = :id", nativeQuery = true)
    PrivateOwner getUserProfile(Long id);


    @Query(value = "select * from user u inner join privateowner p on p.email = u.email where u.email = :email", nativeQuery = true)
    PrivateOwner getUserProfileByEmail(String email);

    @Query(value = "select * from privateowner p inner join  user u  on p.user_id = u.id ORDER BY p.id DESC", nativeQuery = true)
    List<PrivateOwner> getAllPrivateOwners();

    @Query(value = "select * from privateowner p where p.user_id = :id", nativeQuery = true)
    PrivateOwner getOnePrivateOwner(@Param("id") long id);

}
