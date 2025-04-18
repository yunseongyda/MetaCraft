package com.metacraft.assetstore.Entities.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.metacraft.assetstore.Entities.Asset;
import com.metacraft.assetstore.Entities.SiteUser;
import com.metacraft.assetstore.Entities.Repository.AssetRepository;
import com.metacraft.assetstore.Entities.Service.AssetService;
import com.metacraft.assetstore.Entities.Service.SiteUserService;

import groovyjarjarantlr4.v4.parse.ANTLRParser.elementOptions_return;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/api/assets")
@RequiredArgsConstructor
public class AssetController {

  private final AssetService assetService;
  private final SiteUserService userService;
  private final AssetRepository assetRepo;  

  @PostMapping("/upload")
  public String uploadAsset( // ResponseEntity : HTTP 응답을 나타내는 클래스
      @RequestParam("obj") String obj,
      @RequestParam("mtl") String mtl,
      @RequestParam("bd") String bd,
      @RequestParam("images") List<MultipartFile> files,
      Principal principal) {
    // 디버깅
    System.out.println("obj: " + obj);
    System.out.println("mtl: " + mtl);
    System.out.println("bd: " + bd);
    System.out.println("files size: " + files.size());
    SiteUser user = userService.getSiteUser(principal.getName());
    try {
      Asset asset = assetService.uploadAsset(obj, mtl, bd, files);
      asset.setSiteUser(user); // Asset에 SiteUser 설정
      assetRepo.save(asset); // Asset 저장
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "redirect:/api/assets/list";
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/create")
  public String enterCreateAssetPage() {
    return "createAsset";
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/list")
  public String getMethodName(Principal principal, Model model) {
    SiteUser user = userService.getSiteUser(principal.getName());
    List<Asset> assets = user.getAssets();
    model.addAttribute("assets", assets);
    return "asset-list";
  }
  //이름과 썸네일을 바꿔는 함수입니다.
  @PostMapping("/edit-meta")
  public String editMeta(@RequestParam("id") Integer id,
                         @RequestParam("assetName") String name,
                         @RequestParam("thumbnail") MultipartFile thumbnail) {
      //TODO: process POST request
      Asset asse = assetService.getAsset(id); // Asset의 이름 변경
      asse.setName(name);
      try{
         if (thumbnail != null && !thumbnail.isEmpty()) {
          System.out.println(thumbnail.getOriginalFilename());
          assetService.uploadThumbnail(asse, thumbnail); // 썸네일 업로드
        } else {
          System.out.println("썸넬이 비어있음");
        }
        assetRepo.save(asse); // Asset 저장
      } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
      }
      return "redirect:/api/assets/list"; // Redirect to the asset list page after processing 
  }
  
}
