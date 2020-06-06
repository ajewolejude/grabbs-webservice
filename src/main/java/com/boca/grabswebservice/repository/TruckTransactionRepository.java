package com.boca.grabswebservice.repository;

import com.boca.grabswebservice.model.TruckTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TruckTransactionRepository extends JpaRepository<TruckTransaction, Long> {

    @Query(value = "select * from trucktrans t where t.truck_id = :truck_id order by t.id desc limit 1", nativeQuery = true)
    TruckTransaction getCurrentTruckTrans(@Param("truck_id") Long truck_id);

    @Query(value = "select * from trucktrans t ORDER BY t.id DESC", nativeQuery = true)
    List<TruckTransaction> findAllByOrderByIdDesc();

}
