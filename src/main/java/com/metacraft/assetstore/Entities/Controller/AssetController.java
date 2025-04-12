package com.metacraft.assetstore.Entities.Controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.metacraft.assetstore.Entities.Asset;
import com.metacraft.assetstore.Entities.Repository.AssetRepository;
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

  @PostMapping("/upload")
  public ResponseEntity<Asset> uploadAsset( // ResponseEntity : HTTP 응답을 나타내는 클래스
      @RequestParam("obj") String obj,
      @RequestParam("mtl") String mtl,
      @RequestParam("bd") String bd,
      @RequestParam("images") List<MultipartFile> files) {
      // 파일이 업로드되지 않는 경우
      if (files == null || files.isEmpty()) {
        System.out.println("The file was not uploaded");
        return ResponseEntity.badRequest().body(null); // 클라이언트에게 잘못된 요청 응답
      }

      // 디버깅
      System.out.println("obj: " + obj);
      System.out.println("mtl: " + mtl);
      System.out.println("bd: " + bd);
      System.out.println("files size: " + files.size());

    try {
      Asset asset = assetService.uploadAsset(obj, mtl, bd, files);
      
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

}
