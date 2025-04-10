package com.metacraft.assetstore.Entities.Controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.metacraft.assetstore.Entities.Asset;
import com.metacraft.assetstore.Entities.SiteUser;
import com.metacraft.assetstore.Entities.Repository.AssetRepository;
import com.metacraft.assetstore.Entities.Repository.SiteUserRepository;
import com.metacraft.assetstore.Entities.Service.AssetService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/api/assets")
@RequiredArgsConstructor
public class AssetController {

  private final AssetService assetService;
  private final AssetRepository assetRepository;
  private final SiteUserRepository siteUserRepo;

  @PostMapping("/upload")
  public ResponseEntity<Asset> uploadAsset( // ResponseEntity : HTTP 응답을 나타내는 클래스
      @RequestParam("obj") String obj,
      @RequestParam("mtl") String mtl,
      @RequestParam("bd") String bd,
      @RequestParam("images") List<MultipartFile> files,
      Principal principal
    ) {
    
    // 파일이 업로드되지 않는 경우
    if (files == null || files.isEmpty()) {
      System.out.println("The file was not uploaded");
      return ResponseEntity.badRequest().body(null); // 클라이언트에게 잘못된 요청 응답
    }

    // 디버깅
    // System.out.println("obj: " + obj);
    // System.out.println("mtl: " + mtl);
    // System.out.println("bd: " + bd);
    // System.out.println("files size: " + files.size());

    Optional<SiteUser> userName = siteUserRepo.findByusername(principal.getName());

    try {
      Asset asset = assetService.uploadAsset(obj, mtl, bd, files, userName.get().getUsername());

      return ResponseEntity.ok(asset);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().build();
    }

  }

  @GetMapping("/create")
  public String enterUploadAssetPage() {
    return "createAsset";
  }

}
