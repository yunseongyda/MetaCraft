package com.metacraft.assetstore.Entities.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metacraft.assetstore.Entities.Asset;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Integer>{
	
}
