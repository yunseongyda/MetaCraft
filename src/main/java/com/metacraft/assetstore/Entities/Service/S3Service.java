package com.metacraft.assetstore.Entities.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.core.sync.RequestBody;

@Service
@RequiredArgsConstructor
public class S3Service {

  private final S3Client s3Client;

  @Value("${aws.s3.bucket}")
  private String bucketName;

  String folderName = "images/";
  String thumbnailFolderName = "thumbnails/";

  // S3에 파일(이미지) 업로드하고, 업로드된 파일 URL 목록 반환
  public List<String> uploadFiles(List<MultipartFile> files) throws Exception {
    List<String> fileUrls = new ArrayList<>();

    // 오늘 날짜 포멧팅
    String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    // 날짜별로 폴더 경로 추가
    String dateFolder = folderName + currentDate + "/";

    for (MultipartFile file : files) {
      String fileName = dateFolder + System.currentTimeMillis() + "_" + file.getOriginalFilename();

      // 메타데이터를 Map으로 설정
      Map<String, String> metadata = new HashMap<>();
      metadata.put("Content-Type", "image/png");
      metadata.put("Cache-Control", "max-age=86400");

      // PutObjectRequest 생성 (메타데이터 설정)
      PutObjectRequest putObjectRequest = PutObjectRequest.builder()
          .bucket(bucketName)
          .key(fileName)
          .metadata(metadata) // Map으로 메타데이터 설정
          .build();

      // S3에 파일 업로드
      s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

      // 파일 URL 생성
      String fileUrl = s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(fileName)).toString();
      fileUrls.add(fileUrl);
    }

    return fileUrls;
  }

  public String uploadThumbnail(MultipartFile thumbnail) throws Exception {
    // 오늘 날짜 포멧팅
    String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    // 날짜별로 폴더 경로 추가
    String dateFolder = thumbnailFolderName + currentDate + "/";
    String fileName = dateFolder + System.currentTimeMillis() + "_" + thumbnail.getOriginalFilename();

    // 메타데이터를 Map으로 설정
    Map<String, String> metadata = new HashMap<>();
    metadata.put("Content-Type", "image/png");
    metadata.put("Cache-Control", "max-age=86400");

    // PutObjectRequest 생성 (메타데이터 설정)
    PutObjectRequest putObjectRequest = PutObjectRequest.builder()
        .bucket(bucketName)
        .key(fileName)
        .metadata(metadata) // Map으로 메타데이터 설정
        .build();

    // S3에 파일 업로드
    s3Client.putObject(putObjectRequest, RequestBody.fromBytes(thumbnail.getBytes()));
    // 파일 URL 생성
    String fileUrl = s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(fileName)).toString();

    return fileUrl;
  }
}
