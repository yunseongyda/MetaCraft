package com.metacraft.assetstore.Entities.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.metacraft.assetstore.Entities.Asset;
import com.metacraft.assetstore.Entities.Image;
import com.metacraft.assetstore.Entities.Repository.AssetRepository;
import com.metacraft.assetstore.Entities.Repository.ImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {

  private final ImageRepository imgRepo;
  private final AssetRepository assetRepo;
  
  public List<String> getImageUrlsByAssetId(Integer assetId) {
    // Asset을 가져오고 없으면 IllegalArgumentException 발생
    Asset asset = assetRepo.findById(assetId)
                           .orElseThrow(() -> new IllegalArgumentException("Asset not found with ID: " + assetId));
    
    // Asset을 기준으로 이미지를 가져옴
    List<Image> images = asset.getImages();
    
    // 이미지 URL 리스트 반환
    return images.stream()                      // images 리스트를 스트림으로 변환
                 .map(Image::getImageUrl)       // 각 Image 객체에서 URL을 추출
                 .collect(Collectors.toList()); // 추출한 URL들을 리스트에 모아서 반환
  }
}