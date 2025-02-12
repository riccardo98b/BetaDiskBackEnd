package com.betacom.dischi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.dischi.models.Wishlist;

public interface IWishlistRepository extends JpaRepository<Wishlist, Integer>{

}
