package com.metacraft.assetstore.Entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 100)
	private String subject;
	
	private String Introduction;

	private String description;
	
	private String category;

	private int price;
	
	private LocalDateTime createTime;

	private LocalDateTime updateTime;

	private int totalView;

	@OneToMany
	private Set<SiteUser> likeUsers;
	@OneToMany
	private Set<SiteUser> buyUsers;


	@OneToOne
	private Asset modelAsset;

	@ManyToOne
	private SiteUser siteUser;
	
	@OneToMany(mappedBy = "asset")
	private List<Review> comments;
	
}
