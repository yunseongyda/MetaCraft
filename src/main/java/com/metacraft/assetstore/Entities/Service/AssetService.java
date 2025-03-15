package com.metacraft.assetstore.Entities.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.metacraft.assetstore.Entities.Asset;
import com.metacraft.assetstore.Entities.Image;
import com.metacraft.assetstore.Entities.Repository.AssetRepository;
import com.metacraft.assetstore.Entities.Repository.ImageRepository;

import jakarta.mail.Multipart;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssetService {
  
  private final AssetRepository assetRepo;
  private final ImageRepository imageRepo;
  private final S3Service s3Service;

  // S3에 이미지 업로드하고, Asset과 Image 저장하는 로직
  public Asset uploadAsset(String obj, String mtl, String bd, List<MultipartFile> files) throws Exception {
    Asset asset = new Asset();
    asset.setObj(obj);
    asset.setMtl(mtl);
    asset.setBd(bd);

    List<String> fileUrls = s3Service.uploadFiles(files);
    List<Image> images = new ArrayList<>();

    for (String url : fileUrls) {
      Image image = new Image();
      image.setImageUrl(url);
      image.setAsset(asset); // Asset과 연결
      images.add(image);
    }

    asset.setImages(images);
    assetRepo.save(asset); // Asset 저장 -> 자동으로 이미지들도 저장됨

    return asset;
  }
}
