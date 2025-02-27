package com.metacraft.assetstore.Entities.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.metacraft.assetstore.Entities.Service.ProductService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

  private final ProductService productService;

  @GetMapping("/detail")
  public String ProductInfo(){
    return "shop-details";
  }
}
