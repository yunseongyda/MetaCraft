package com.metacraft.assetstore.Entities.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metacraft.assetstore.Entities.Wishlist;
import com.metacraft.assetstore.Entities.SiteUser;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer>{

}
