package com.metacraft.assetstore.Entities.Controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.metacraft.assetstore.Entities.Product;
import com.metacraft.assetstore.Entities.Review;
import com.metacraft.assetstore.Entities.SiteUser;
import com.metacraft.assetstore.Entities.Service.ProductService;
import com.metacraft.assetstore.Entities.Service.SiteUserService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

  private final ProductService productService;
  private final SiteUserService userService;

  @GetMapping("/detail/{p_id}")
  public String ProductInfo(@PathVariable("p_id")Integer id, Model model){
    Product product = productService.getProduct(id);
    model.addAttribute("product", product);
    int sum = 0;
    for (Review review : product.getComments()) {
       sum += review.getRating();
    }
    System.out.println(product.getComments().size());
    if (product.getComments().size() != 0)
      model.addAttribute("rating", (float)sum / product.getComments().size());
    else
      model.addAttribute("rating", 0);
    model.addAttribute("asset", product.getModelAsset());
    return "product-details";
  }
  
  @PreAuthorize("isAuthenticated()")
  @GetMapping("/like/{p_id}")
  public String ProductLike(@PathVariable("p_id")Integer id, Model model, Principal principal){
    Product product = productService.getProduct(id);
    model.addAttribute("product", product);
    int sum = 0;
    for (Review review : product.getComments()) {
      sum += review.getRating();
    }
    
    model.addAttribute("rating", sum / (float)product.getComments().size());
    model.addAttribute("asset", product.getModelAsset());
    return "product-details";
  }  
  @PreAuthorize("isAuthenticated()")
  @GetMapping("/get/{p_id}")
  public String getProduct(@PathVariable("p_id")Integer id, Model model, Principal principal){
    SiteUser user = userService.getSiteUser(principal.getName());
    Product product = productService.getProduct(id);
    user.getProducts().add(product);

    
    
    return "redirect:/";
  }
}
