package com.metacraft.assetstore.Entities.Controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.metacraft.assetstore.Entities.Service.ImageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

  private final ImageService imgService;

  @GetMapping("/getUrl")
  public String getImageUrls(@RequestParam("num") Integer assetId, Model model) {
    List<String> imageUrls = imgService.getImageUrlsByAssetId(assetId);
    model.addAttribute("imageUrls", imageUrls);
    return "test";
  }
}
