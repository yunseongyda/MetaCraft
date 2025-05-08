package com.metacraft.assetstore.Entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Asset {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(columnDefinition = "TEXT", length=999999999)
  private String obj;

  @Column(columnDefinition = "TEXT", length=999999999)
  private String mtl;

  @Column(columnDefinition = "TEXT", length=999999999)
  private String bd;

  //에셋 이름 
  @Size(max = 100, message = "이름은 100자 이내로 입력해주세요.")
  private String name;

  // @Version
  // private Integer version;
  @ManyToOne
  private SiteUser siteUser; // Asset을 업로드한 사용자

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Image> images; // 하나의 Asset이 여러 개의 이미지를 가질 수 있도록 설정

  private String maker;

  //todo : 질감마다 얼마나 사용했느지 
  @OneToOne(cascade = CascadeType.ALL)
  private Image thumbnail; // Asset의 썸네일 이미지
}