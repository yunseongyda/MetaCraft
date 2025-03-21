package com.metacraft.assetstore.Entities.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metacraft.assetstore.Entities.SiteUser;

@Repository
public interface SiteUserRepository extends JpaRepository<SiteUser, Integer>{
  Optional<SiteUser> findByusername(String username);
  boolean existsByUsername(String username);
  boolean existsByEmail(String email);
}
