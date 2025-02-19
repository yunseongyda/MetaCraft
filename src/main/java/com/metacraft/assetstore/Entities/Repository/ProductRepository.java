package com.metacraft.assetstore.Entities.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metacraft.assetstore.Entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
}
