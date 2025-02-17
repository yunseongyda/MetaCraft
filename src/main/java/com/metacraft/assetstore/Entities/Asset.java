package com.metacraft.assetstore.Entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Asset {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 100)
	private String subject;
	
	private int price;
	
	private String content;
	
	private String objfile;
	
	private String bdfile;
	
	@ManyToOne
	private SiteUser siteUser;
	
	@OneToMany(mappedBy = "asset")
	private List<Comment> comments;
	
}
