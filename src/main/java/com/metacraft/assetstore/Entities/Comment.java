package com.metacraft.assetstore.Entities;
<<<<<<<< HEAD:src/main/java/com/metacraft/assetstore/Entities/Review.java

import java.time.LocalDateTime;
========
>>>>>>>> origin/main:src/main/java/com/metacraft/assetstore/Entities/Comment.java

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String content;
	
	@ManyToOne
	private SiteUser siteUser;
	
	@ManyToOne
	private Product asset;
	
	private LocalDateTime createTime;

	private int rating;
	
}
