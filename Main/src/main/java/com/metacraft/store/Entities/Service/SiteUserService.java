package com.metacraft.store.Entities.Service;

import org.springframework.stereotype.Service;

import com.metacraft.store.Entities.Repository.SiteUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SiteUserService {
	private final SiteUserRepository userRepo;
}
