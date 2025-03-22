package com.metacraft.assetstore.Entities;

import java.util.List;

import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Asset {

  @Id
  @Nullable
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @Column(columnDefinition = "TEXT")
  private String obj;
  
  @Column(columnDefinition = "TEXT")
  private String mtl;
  
  @Column(columnDefinition = "TEXT")
  private String bd;

  // @Version
  // private Integer version;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Image> images; // 하나의 Asset이 여러 개의 이미지를 가질 수 있도록 설정

  //todo : 질감마다 얼마나 사용했느지 
}
