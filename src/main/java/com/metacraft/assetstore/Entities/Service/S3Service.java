package com.metacraft.assetstore.Entities.Service;

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

  public String uploadFile(MultipartFile file) throws Exception { 
    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename(); // 파일 이름을 현재 시간과 파일 이름으로 설정 -> 중복 방지
    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentLength(file.getSize()); // S3에 올라갈 파일 크기 설정
    s3Client.putObject(bucketName, fileName, file.getInputStream(), metadata); // S3에 파일 업로드

    return s3Client.getUrl(bucketName, fileName).toString(); // 업로드된 파일의 URL 반환;
  }
}
