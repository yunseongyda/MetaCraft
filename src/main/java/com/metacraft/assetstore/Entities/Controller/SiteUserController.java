package com.metacraft.assetstore.Entities.Controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.metacraft.assetstore.Entities.Form.RegisterUserForm;
import com.metacraft.assetstore.Entities.Service.SiteUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/siteuser")
public class SiteUserController {

  private final SiteUserService siteUserService;
  
  // 회원가입
  @GetMapping("/register")
  public String register(RegisterUserForm registerUserForm) {
    return "register";
  }

  // 회원가입 처리
  @PostMapping("/register")
  public String createUser(@Valid RegisterUserForm registerUserForm, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "register";
    }

    // 비밀번호 확인
    if (!registerUserForm.getPassword1().equals(registerUserForm.getPassword2())) {
      bindingResult.rejectValue("password2", "passwordInCorrect", "The password does not match.");
      return "register";
    }

    // 중복확인
    try {
      siteUserService.create(registerUserForm.getUsername(), registerUserForm.getEmail(), registerUserForm.getPassword1());
    // } catch(DataIntegrityViolationException e) {
    //   e.printStackTrace();
    //   bindingResult.rejectValue("username", "duplicated", "The username is already in use.");
    //   return "register";
    } catch(IllegalArgumentException e) {
      if (e.getMessage().contains("username")) {
        bindingResult.rejectValue("username", "duplicated", "The username is already in use.");
      } else if (e.getMessage().contains("email")) {
        bindingResult.rejectValue("email", "duplicated", "The email is already in use.");
      }
      return "register";
    }
    catch(Exception e) {
      e.printStackTrace();
      bindingResult.rejectValue("registerFailed", e.getMessage());
      return "register";
    }
    
    return "redirect:/siteuser/login";

  }

  // 로그인
  @GetMapping("/login")
  public String login() {
    return "login";
  }

  // 프로필
  @GetMapping("/profile")
  public String profile() {
    return "profile";
  }
}