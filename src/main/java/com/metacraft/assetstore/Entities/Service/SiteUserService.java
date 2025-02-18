package com.metacraft.assetstore.Entities.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.metacraft.assetstore.Entities.SiteUser;
import com.metacraft.assetstore.Entities.Repository.SiteUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SiteUserService {
	private final SiteUserRepository userRepo;
	private final PasswordEncoder passwordEncoder;

	public SiteUser create (String username, String email, String pasword){
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(pasword));
		return userRepo.save(user);
	}
}
