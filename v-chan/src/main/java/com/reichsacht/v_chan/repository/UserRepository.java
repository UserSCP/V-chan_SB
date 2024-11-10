package com.reichsacht.v_chan.repository;

import org.springframework.stereotype.Repository;
import com.reichsacht.v_chan.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByUsername(String username);

}
