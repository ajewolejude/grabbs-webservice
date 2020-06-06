package com.boca.grabswebservice.repository;

import com.boca.grabswebservice.model.TruckRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TruckRequestRepository extends JpaRepository<TruckRequest, Long> {


    @Query(value = "select * from TruckRequest t inner join  merchant m  on t.merchant_id = m.id inner join  user u  on m.user_id = u.id where u.id=:id ORDER BY t.id DESC", nativeQuery = true)
    List<TruckRequest> getRequestByMerchantId(@Param("id") Long id);

    @Query(value = "select * from TruckRequest t where t.deleted_by = 0 ORDER BY t.id DESC", nativeQuery = true)
    List<TruckRequest> findAllByOrderByIdDesc();
}