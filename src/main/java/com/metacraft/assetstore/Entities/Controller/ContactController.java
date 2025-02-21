package com.metacraft.assetstore.Entities.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.metacraft.assetstore.Entities.Service.EmailService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class ContactController {

  private final EmailService emailService;
  @GetMapping("/contact")
  public String conatact() {
      return "contact"; 
  }


  @PostMapping("/contact")
  public String contact(@RequestParam("subject") String subject, @RequestParam("content") String content, @RequestParam("email") String userEmail) {
      emailService.sendEmail(userEmail, subject, content);

      return "redirect:/";
  }
  
}
