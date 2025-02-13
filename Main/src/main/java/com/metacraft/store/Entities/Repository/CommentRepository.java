package com.metacraft.store.Entities.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metacraft.store.Entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
