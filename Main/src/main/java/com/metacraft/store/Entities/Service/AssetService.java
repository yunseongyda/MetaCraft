package com.metacraft.store.Entities.Service;

import org.springframework.stereotype.Service;

import com.metacraft.store.Entities.Repository.AssetRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssetService {
	private final AssetRepository assetRepo;
	
	
}
