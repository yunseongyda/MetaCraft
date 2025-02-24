package com.metacraft.assetstore.Entities.Controller;

import java.security.Principal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.metacraft.assetstore.Entities.SiteUser;
import com.metacraft.assetstore.Entities.Service.EmailService;
import com.metacraft.assetstore.Entities.Service.SiteUserService;


import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;


@Controller
@RequiredArgsConstructor
@RequestMapping("/inquire")
public class InquiryController {

  private final EmailService emailService;
  private final SiteUserService siteuserService;

  @GetMapping("")
  public String contact(Principal principal, Model model) {
    if (principal != null){
      SiteUser user = siteuserService.getSiteUser(principal.getName());
      model.addAttribute("user", user);
    }
    return "inquire"; 
  }


  @PostMapping("/send")
  public String contact(@RequestParam("subject") String subject, @RequestParam("content") String content, @RequestParam("email") String userEmail) {
      emailService.sendEmail(userEmail, subject, content);

      return "redirect:/";
  }
  
}
