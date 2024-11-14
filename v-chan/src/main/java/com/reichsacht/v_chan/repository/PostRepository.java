package com.reichsacht.v_chan.repository;

import org.springframework.stereotype.Repository;
import com.reichsacht.v_chan.model.Post;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long>{
    Optional<Post> findByPost(String post);

}
