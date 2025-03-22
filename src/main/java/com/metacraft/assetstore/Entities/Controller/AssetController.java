package com.metacraft.assetstore.Entities.Controller;

import java.util.List;

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
  public String EnteruploadAsset() {
    return "createAsset";
  }

}
