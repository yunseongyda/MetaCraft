package com.metacraft.assetstore.Entities.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

  @GetMapping("/contact")
  public String conatact() {
      return "contact"; 
  }
  
}
