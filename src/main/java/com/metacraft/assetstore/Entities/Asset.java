package com.metacraft.assetstore.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Asset {

  @Id
  private Integer id;
  
  private String obj;
  
  private String mtl;
  
  private String bd;

  //todo : 질감마다 얼마나 사용했느지 
}
