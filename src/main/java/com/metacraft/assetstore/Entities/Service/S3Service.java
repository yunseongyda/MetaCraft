package com.metacraft.assetstore.Entities.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class S3Service {

  private final AmazonS3 s3Client;

  @Value("${aws.s3.bucket}") // application.properties에 있는 aws.s3.bucket 값을 가져옴
  private String bucketName;

  // S3에 파일(이미지) 업로드하고, 업로드된 파일 URL 목록 반환
  public List<String> uploadFiles(List<MultipartFile> files) throws Exception {
    List<String> fileUrls = new ArrayList<>();

    for (MultipartFile file : files) {
      String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
      ObjectMetadata metadata = new ObjectMetadata();
      s3Client.putObject(bucketName, fileName, file.getInputStream(), metadata);
      
      fileUrls.add(s3Client.getUrl(bucketName, fileName).toString());
    }

    return fileUrls;
  }
}
