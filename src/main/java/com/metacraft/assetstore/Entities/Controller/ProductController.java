package com.metacraft.assetstore.Entities.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.metacraft.assetstore.Entities.Product;
import com.metacraft.assetstore.Entities.Service.ProductService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

  private final ProductService productService;
  @GetMapping("/detail/{p_id}")
  public String ProductInfo(@PathVariable("p_id")Integer id, Model model){
    Product product = productService.getProduct(id);
    model.addAttribute("product", product);

    model.addAttribute("asset", product.getModelAsset());
    return "product-details";
  }
}
