package com.boca.grabswebservice.repository;

import com.boca.grabswebservice.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query(value = "select * from document d where d.owner_id= :owner_id and d.document_category= :document_category",
            nativeQuery=true)
    List<Document> getDocuments(@Param("owner_id") Long owner_id, @Param("document_category") String document_category);


}
