package com.metacraft.assetstore.Entities.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metacraft.assetstore.Entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	public List<Product> findByCategory(String category);
}
