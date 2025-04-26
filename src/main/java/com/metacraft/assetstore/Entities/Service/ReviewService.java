package com.metacraft.assetstore.Entities.Service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.metacraft.assetstore.Entities.Product;
import com.metacraft.assetstore.Entities.Review;
import com.metacraft.assetstore.Entities.SiteUser;
import com.metacraft.assetstore.Entities.Repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewService {
	private final ReviewRepository reviewRepo;
	
	//댓글 엔티티 생성 후 데이터 베이스에 저장
	public void createReview(String content, int rating, Product product, SiteUser user) {
		Review review = new Review();
		review.setContent(content);
		review.setRating(rating);
		review.setProduct(product);;
		review.setSiteUser(user);;
		review.setCreateTime(LocalDateTime.now());
		reviewRepo.save(review);
	}
}
