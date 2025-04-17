package com.metacraft.assetstore.Entities.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metacraft.assetstore.Entities.Asset;
import com.metacraft.assetstore.Entities.Image;
import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer>{
  
  List<Image> findByAsset(Asset asset); // Asset 엔티티를 기준으로 이미지 검색
}
