package com.apiPractise.demo.Repository;

import com.apiPractise.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.name like (%:name%) and u.phoneNumber like (%:phoneNumber%)")
    //@Query(value = "select * from USER where NAME like (%:name%)", nativeQuery = true)
    List<User> findByName(@Param("name") String name, @Param("phoneNumber") String phoneNumber);
}
