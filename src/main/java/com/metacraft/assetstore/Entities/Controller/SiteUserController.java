package com.metacraft.assetstore.Entities.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.metacraft.assetstore.Entities.Service.SiteUserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/siteuser")
public class SiteUserController {

  private final SiteUserService siteUserService;
  
  @GetMapping("/register")
  public String register() {
    return "user/register";
  }

  @GetMapping("/login")
  public String login() {
    return "user/login";
  }
}