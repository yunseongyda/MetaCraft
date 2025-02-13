package com.metacraft.store.Entities.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metacraft.store.Entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
