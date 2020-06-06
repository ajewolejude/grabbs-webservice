package com.boca.grabswebservice.repository;

import com.boca.grabswebservice.model.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, Long> {

    @Query(value = "SELECT * FROM accessory a where a.truck_id =:truck_id", nativeQuery = true)
    List<Accessory> getTruckAccessory(Long truck_id);



}
