package com.boca.grabswebservice.repository;

import com.boca.grabswebservice.model.AccessoryAgg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessoryAggRepository extends JpaRepository<AccessoryAgg, Long> {

    @Query(value = "SELECT * FROM accessoryagg", nativeQuery = true)
    List<AccessoryAgg> getAvailableAccessory();


    @Modifying
    @Query("update AccessoryAgg a set a.agg = :agg where a.type= :type ")
    void softDelete(@Param("agg") Long agg, @Param("type") String type);

    @Modifying
    @Query("update AccessoryAgg a set a.agg = :agg where a.id= :id ")
    void updateById(@Param("id") Long id, @Param("agg") Long agg);


    @Query(value = "SELECT * FROM accessoryagg a where a.type =:type", nativeQuery = true)
    AccessoryAgg getByType(String type);

    @Query(value = "SELECT a.agg FROM accessoryagg a where a.id =:id", nativeQuery = true)
    Long getById(@Param("id") int i);

    @Query(value = "SELECT * FROM accessoryagg a where a.id =:id", nativeQuery = true)
    AccessoryAgg getAllById(@Param("id") int i);
}
