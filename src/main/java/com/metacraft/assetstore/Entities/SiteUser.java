package com.metacraft.assetstore.Entities;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SiteUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true)
	private String email;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	
	//산 에셋들
	@ManyToMany(mappedBy = "buyUsers")
	private List<Product> products;

	@OneToMany(mappedBy = "siteUser")
	private List<Asset> assets;


	@OneToMany(mappedBy = "siteUser")
	private List<Review> comments;
  
	@ManyToMany(mappedBy = "likeUsers")
	private Set<Product> likedProducts;
}
