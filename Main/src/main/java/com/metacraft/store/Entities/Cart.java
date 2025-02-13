package com.metacraft.store.Entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cart {

	@Id
	private SiteUser Siteuser;
	
	@OneToMany
	private List<Asset> asset;
}
