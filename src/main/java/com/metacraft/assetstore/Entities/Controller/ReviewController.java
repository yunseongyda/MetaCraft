package com.metacraft.assetstore.Entities.Controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.metacraft.assetstore.Entities.Product;
import com.metacraft.assetstore.Entities.SiteUser;
import com.metacraft.assetstore.Entities.Service.ProductService;
import com.metacraft.assetstore.Entities.Service.ReviewService;
import com.metacraft.assetstore.Entities.Service.SiteUserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
  private final ReviewService reviewService;
  private final ProductService productService;
  private final SiteUserService userService;

  //리뷰 작성릏 위한 메소드
  @PreAuthorize("isAuthenticated()")
  @PostMapping("/send/{p_id}")
  public String SendComment(@PathVariable("p_id") Integer id,
   @RequestParam("content") String content,
   @RequestParam("rating") int rating,
   Principal principal) {

    SiteUser user = userService.getSiteUser(principal.getName());
    Product product = productService.getProduct(id);
    reviewService.createReview(content, rating, product, user);

    return "redirect:/product/detail/" + id;
  }
  
}
