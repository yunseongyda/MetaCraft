package com.metacraft.assetstore.Entities.Service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.metacraft.assetstore.Entities.Cart;
import com.metacraft.assetstore.Entities.Product;
import com.metacraft.assetstore.Entities.Repository.CartRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartService {
	private final CartRepository cartRepo;

	public Cart getCart(Integer id) {
		return cartRepo.findById(id).orElse(null);
	}
	public Cart createCart(Integer id) {
		Cart cart = new Cart();
		cart.setUserId(id);
		cartRepo.save(cart);
		return cart;
	}
	public void addAssetToCart(Cart cart, Product product) {
		if (cart.getAsset() == null) {
			cart.setAsset(new ArrayList<>());
		}
		cart.getAsset().add(product);
		cartRepo.save(cart);
	}
	public void deleteAssetFromCart(Cart cart, Product product) {
		cart.getAsset().remove(product);
		cartRepo.save(cart);
	}
}
