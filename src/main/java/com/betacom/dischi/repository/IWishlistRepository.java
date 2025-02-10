package com.betacom.dischi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.dischi.models.Cliente;
import com.betacom.dischi.models.Prodotto;
import com.betacom.dischi.models.Wishlist;

public interface IWishlistRepository extends JpaRepository<Wishlist, Integer>{

	Optional<Wishlist> findByCliente(Cliente cliente);
	
	
	    @Query("SELECT w FROM Wishlist w WHERE w.cliente.id = :idCliente")
	    Wishlist findByCliente(@Param("idCliente") Integer idCliente);

	    @Query("SELECT w FROM Wishlist w WHERE w.idWishlist = :idWishlist")
	    Optional<Wishlist> findById(@Param("idWishlist") Integer idWishlist);

	    @Query("SELECT p FROM Wishlist w JOIN w.prodotti p WHERE w.cliente.id = :idCliente")
	    List<Prodotto> getAllProductsByCliente(@Param("idCliente") Integer idCliente);

	    @Query("SELECT COUNT(p) FROM Wishlist w JOIN w.prodotti p WHERE w.cliente.id = :idCliente AND p.id = :idProdotto")
	    Long checkProductInWishlist(@Param("idCliente") Integer idCliente, @Param("idProdotto") Integer idProdotto);

}
