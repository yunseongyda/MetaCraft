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

  @GetMapping("/list")
  public String ProductList(){
    return "shop-grid";
  }

  @GetMapping("/char")
  public String ProductChar(){
    return "shop-characters";
  }
  @GetMapping("/envi")
  public String ProductEnvi(){
    return "shop-environments";
  }
  @GetMapping("/props")
  public String ProductProps(){
    return "shop-props";
  }
  @GetMapping("/vehi")
  public String ProductVehi(){
    return "shop-vehicles";
  }
  @GetMapping("/archi")
  public String ProductArchi(){
    return "shop-architecture";
  }
}
