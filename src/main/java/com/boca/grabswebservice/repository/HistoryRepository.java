package com.boca.grabswebservice.repository;

import com.boca.grabswebservice.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query(value = "select * from history d where d.type_id= :type_id and d.type= :type",
            nativeQuery=true)
    List<History> getHistory(@Param("type_id") Long type_id, @Param("type") String type);

    @Query(value = "select * from history d where d.type_id= :type_id and d.type= :type and d.sub_type= :sub_type ORDER BY d.id DESC",
            nativeQuery=true)
    List<History> getHistoryBySubtype(@Param("type_id") Long type_id, String type, String sub_type);

    @Query(value = "select * from history d where d.type_id= :type_id and d.type= :type and (d.sub_type= :sub_type1 or d.sub_type= :sub_type2 ORDER BY d.id DESC)",
            nativeQuery=true)
    List<History> getHistoryByRepair(@Param("type_id") Long type_id, String type, String sub_type1, String sub_type2);
}