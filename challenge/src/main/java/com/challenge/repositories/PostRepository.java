package com.challenge.repositories;

import com.challenge.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUserIdInOrderByDateDesc(List<Long> userIds);
    List<Post> findAllByUserIdInOrderByDateAsc(List<Long> userIds);
}
