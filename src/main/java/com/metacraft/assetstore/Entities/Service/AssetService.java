package com.metacraft.assetstore.Entities.Service;

import org.springframework.stereotype.Service;

import com.metacraft.assetstore.Entities.Repository.AssetRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssetService {
	private final AssetRepository assetRepo;
	
	
}
