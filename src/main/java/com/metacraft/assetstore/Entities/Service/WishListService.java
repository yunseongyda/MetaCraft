package com.metacraft.assetstore.Entities.Service;

import org.springframework.stereotype.Service;

import com.metacraft.assetstore.Entities.WishList;
import com.metacraft.assetstore.Entities.Repository.WishListRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WishListService {
	private final WishListRepository wishListRepo;
}
