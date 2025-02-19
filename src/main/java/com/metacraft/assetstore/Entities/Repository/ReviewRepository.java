package com.metacraft.assetstore.Entities.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metacraft.assetstore.Entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{

}
