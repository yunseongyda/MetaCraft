package com.metacraft.assetstore.Entities.Service;

import org.springframework.stereotype.Service;

import com.metacraft.assetstore.Entities.Wishlist;
import com.metacraft.assetstore.Entities.Repository.WishlistRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WishlistService {
	private final WishlistRepository wishlistRepo;
}
