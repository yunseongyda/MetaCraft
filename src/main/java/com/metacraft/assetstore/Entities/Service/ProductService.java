package com.metacraft.assetstore.Entities.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.metacraft.assetstore.Entities.Asset;
import com.metacraft.assetstore.Entities.Product;
import com.metacraft.assetstore.Entities.Repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepo;
	
	//프로덕트를 생성하는 메소드
	// Asset을 매개변수로 받아서 Product를 생성하고 저장하는 메소드
	public void createProduct(Asset asset, String subject,String introduction, String description, String category, int price) {
		Product product = new Product();
		product.setModelAsset(asset);
		product.setSubject(subject);
		product.setIntroduction(introduction);
		product.setDescription(description);
		product.setCategory(category);
		product.setCreateTime(LocalDateTime.now());
		product.setPrice(price);
		
		productRepo.save(product);
	}
	public Product getProduct(Integer id) {
		return productRepo.findById(id).orElse(null);
	}

	public List<Product> getAllProducts(){
		return productRepo.findAll();
	}
	public List<Product> getProductsByCategory(String category){
		return productRepo.findByCategory(category);
	}
}
