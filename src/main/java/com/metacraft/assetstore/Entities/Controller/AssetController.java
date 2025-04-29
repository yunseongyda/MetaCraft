package com.metacraft.assetstore.Entities.Controller;

import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.metacraft.assetstore.Entities.Asset;
import com.metacraft.assetstore.Entities.Product;
import com.metacraft.assetstore.Entities.SiteUser;
import com.metacraft.assetstore.Entities.Form.CreateProductForm;
import com.metacraft.assetstore.Entities.Repository.AssetRepository;
import com.metacraft.assetstore.Entities.Service.AssetService;
import com.metacraft.assetstore.Entities.Service.ProductService;
import com.metacraft.assetstore.Entities.Service.SiteUserService;

import groovyjarjarantlr4.v4.parse.ANTLRParser.elementOptions_return;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/api/assets")
@RequiredArgsConstructor
public class AssetController {

  private final AssetService assetService;
  private final SiteUserService userService;
  private final AssetRepository assetRepo;
  private final ProductService productService;

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
  public String enterUploadAssetPage() {
    return "createAsset";
  }

  @GetMapping("obj")
  public ResponseEntity<byte[]> downloadObj(@RequestParam("id") Integer assetId) {
    Asset asset = assetService.findById(assetId); // AssetService에서 id로 조회하는 메서드 필요
    String objText = asset.getObj(); // obj 문자열

    byte[] objBytes = objText.getBytes(StandardCharsets.UTF_8);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.TEXT_PLAIN);
    headers.setContentDispositionFormData("attachment", "model.obj");

    return ResponseEntity.ok()
        .headers(headers)
        .body(objBytes);
  }

  @GetMapping("mtl")
  public ResponseEntity<byte[]> downloadMtl(@RequestParam("id") Integer assetId) {
    Asset asset = assetService.findById(assetId);
    String mtlText = asset.getMtl();

    byte[] mtlBytes = mtlText.getBytes(StandardCharsets.UTF_8);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.TEXT_PLAIN);
    headers.setContentDispositionFormData("attachment", "model.mtl");

    return ResponseEntity.ok()
        .headers(headers)
        .body(mtlBytes);
  }

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

  // 이름과 썸네일을 바꿔는 함수입니다.
  @PostMapping("/edit-meta")
  public String editMeta(@RequestParam("id") Integer id,
      @RequestParam("assetName") String name,
      @RequestParam("thumbnail") MultipartFile thumbnail) {
    // TODO: process POST request
    Asset asse = assetService.getAsset(id); // Asset의 이름 변경
    asse.setName(name);
    try {
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
  @PreAuthorize("isAuthenticated()")
  @GetMapping("release/{id}")
  public String releaseAsset(@PathVariable("id") Integer id,CreateProductForm createProductForm, Model model, Principal principal) {

    Asset asset = assetService.getAsset(id); // Asset 조회
    SiteUser user = userService.getSiteUser(principal.getName()); // 현재 사용자 조회
    if (asset.getSiteUser() != user)
      return "redirect:/api/assets/list"; // Asset의 소유자가 아닌 경우 리다이렉트
    model.addAttribute("asset", asset); // Asset bd를 모델에 추가
    createProductForm.setSubject(asset.getName()); // Asset을 폼에 설정
    return "asset-release";
  }

  @PostMapping("release/{id}")
  public String releaseAsset(@PathVariable("id") Integer id, 
  @Valid CreateProductForm createProductForm,
  BindingResult bindingResult, Model model) {
    Asset asset = assetService.getAsset(id); // Asset 조회
    if (bindingResult.hasErrors()) {
      model.addAttribute("asset", asset); // Asset bd를 모델에 추가
      return "asset-release"; // Return to the form if there are validation errors
    }
    System.out.println("createProductForm: " + createProductForm.getPrice());
    productService.createProduct(asset, createProductForm.getSubject(),
      createProductForm.getIntroduction(),
      createProductForm.getDescription(),
      createProductForm.getCategory(),
      createProductForm.getPrice()); // Product 생성

    return "redirect:/"; // Redirect to the index page after processing
  }
}