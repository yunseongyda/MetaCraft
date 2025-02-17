package com.metacraft.assetstore.Entities.Service;

import org.springframework.stereotype.Service;

import com.metacraft.assetstore.Entities.Repository.SiteUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SiteUserService {
	private final SiteUserRepository userRepo;
}
