package com.metacraft.store.Entities.Service;

import org.springframework.stereotype.Service;

import com.metacraft.store.Entities.Repository.CartRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartService {
	private final CartRepository cartRepo;
}
