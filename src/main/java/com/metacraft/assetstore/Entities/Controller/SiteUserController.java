package com.metacraft.assetstore.Entities.Controller;

import org.springframework.stereotype.Controller;
<<<<<<< HEAD
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.metacraft.assetstore.Entities.Form.UserCreateForm;
import com.metacraft.assetstore.Entities.Service.SiteUserService;

import jakarta.validation.Valid;
=======
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.metacraft.assetstore.Entities.Service.SiteUserService;

>>>>>>> origin/main
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/siteuser")
public class SiteUserController {

  private final SiteUserService siteUserService;
  
  @GetMapping("/register")
<<<<<<< HEAD
  public String register(Model model) {
    model.addAttribute("form", new UserCreateForm());
    return "register";
  }

  @PostMapping("/register")
  public String createUser(@Valid UserCreateForm createForm, BindingResult bindingResult, UserCreateForm form) {
    if (bindingResult.hasErrors()) {
      return "register";
    }

    if (!createForm.getPassword1().equals(createForm.getPassword2())) {
      bindingResult.rejectValue("password2", "passwordInCorrect", "비밀번호가 일치하지 않습니다.");
      return "register";
    }
    siteUserService.create(createForm.getUsername(), createForm.getEmail(), createForm.getPassword1());
    return "redirect:/siteuser/login";

=======
  public String register() {
    return "user/register";
>>>>>>> origin/main
  }

  @GetMapping("/login")
  public String login() {
<<<<<<< HEAD
    return "login";
=======
    return "user/login";
>>>>>>> origin/main
  }
}