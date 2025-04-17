package com.metacraft.assetstore.Entities.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.metacraft.assetstore.Entities.Service.ImageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestContoller {

  private final ImageService imgService;

  @GetMapping("/lab")
  public String lab() {
    return "test";
  }

  @GetMapping("/searchImage")
  public String getImageUrls(@RequestParam("num") Integer assetId, Model model) {
    List<String> imageUrls = imgService.getImageUrlsByAssetId(assetId);
    model.addAttribute("imageUrls", imageUrls);
    model.addAttribute("assetId", assetId);
    return "test";
  }
}
