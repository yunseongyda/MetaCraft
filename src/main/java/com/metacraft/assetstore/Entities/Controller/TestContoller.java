package com.metacraft.assetstore.Entities.Controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.metacraft.assetstore.Entities.SiteUser;
import com.metacraft.assetstore.Entities.Repository.SiteUserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestContoller {

  private final SiteUserRepository siteUserRepo;
  
  @GetMapping("/lab")
  public String lab() {
    return "test";
  }

  @Secured("ROLE_USER")
  @GetMapping("/user-id")
  public ResponseEntity<String> getUserId(Principal principal) {
    Optional<SiteUser> user = siteUserRepo.findByusername(principal.getName());
    if (user == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
    }
    return ResponseEntity.ok("User ID: " + user.get().getId());
}
}
