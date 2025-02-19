package com.metacraft.assetstore.Entities.Controller;

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
  
  @GetMapping("/register")
  public String register(Model model) {
    model.addAttribute("form", new RegisterUserForm());
    return "register";
  }

  @PostMapping("/register")
  public String createUser(@Valid RegisterUserForm createForm, BindingResult bindingResult, RegisterUserForm form) {
    if (bindingResult.hasErrors()) {
      return "register";
    }

    if (!createForm.getPassword1().equals(createForm.getPassword2())) {
      bindingResult.rejectValue("password2", "passwordInCorrect", "비밀번호가 일치하지 않습니다.");
      return "register";
    }
    siteUserService.create(createForm.getUsername(), createForm.getEmail(), createForm.getPassword1());
    return "redirect:/siteuser/login";

  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }
}