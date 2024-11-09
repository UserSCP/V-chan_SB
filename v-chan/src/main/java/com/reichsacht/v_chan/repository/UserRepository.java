package com.reichsacht.v_chan.repository;

import org.springframework.stereotype.Repository;
import com.reichsacht.v_chan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}
