package com.metacraft.store.Entities.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metacraft.store.Entities.Cart;
import com.metacraft.store.Entities.SiteUser;

@Repository
public interface CartRepository extends JpaRepository<Cart, SiteUser>{

}
