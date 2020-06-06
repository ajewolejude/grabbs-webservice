package com.boca.grabswebservice.repository;

import com.boca.grabswebservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User getById(Long id);

    @Query(value = "select * from grabbs.user u inner join grabbs.users_roles ur on u.id = ur.user_id   inner join grabbs.role r on r.id = ur.role_id  where u.email= :email", nativeQuery = true)
    User findByEmail(String email);

    @Query(value = "select * from user u where u.email = :email", nativeQuery = true)
    User getByEmail(String email);
}
