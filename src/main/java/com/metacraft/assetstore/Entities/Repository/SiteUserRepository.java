package com.metacraft.assetstore.Entities.Repository;
<<<<<<< HEAD

import java.util.Optional;
=======
>>>>>>> origin/main

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metacraft.assetstore.Entities.SiteUser;

@Repository
public interface SiteUserRepository extends JpaRepository<SiteUser, Integer>{
  Optional<SiteUser> findByusername(String username);
}
