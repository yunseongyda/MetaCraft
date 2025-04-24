package com.metacraft.assetstore.Entities.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.metacraft.assetstore.Entities.Product;
import com.metacraft.assetstore.Entities.SiteUser;
import com.metacraft.assetstore.Entities.Service.ReviewService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
  private final ReviewService reviewService;

  public void SendComment(String content, int rating, Product product, SiteUser user) {
    reviewService.createReview(content, rating, product, user);

  }
  
}
