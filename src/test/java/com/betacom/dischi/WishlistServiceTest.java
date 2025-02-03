package com.betacom.dischi;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.dischi.services.interfaces.WishlistService;
import com.betacom.dischi.request.WishlistRequest;
import com.betacom.dischi.DTO.WishlistDTO;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WishlistServiceTest {

    @Autowired
    WishlistService wishlistService;
    
    @Autowired
    Logger log;

    @Test
    @Order(1)
    public void createWishlistTest() throws Exception {
        WishlistRequest req = new WishlistRequest();
        req.setClienteId(1);
        req.setProdottiIds(Arrays.asList(1, 2, 3));

        wishlistService.create(req);

        WishlistDTO wishlist = wishlistService.getById(1);
        Assertions.assertThat(wishlist).isNotNull();
        Assertions.assertThat(wishlist.getClienteId()).isEqualTo(1);
        Assertions.assertThat(wishlist.getProdottiIds()).containsExactlyInAnyOrder(1, 2, 3);
    }

    @Test
    @Order(2)
    public void updateWishlistTest() throws Exception {
        WishlistRequest req = new WishlistRequest();
        req.setIdWishlist(1);
        req.setProdottiIds(Arrays.asList(4, 5));

        wishlistService.update(req);

        WishlistDTO wishlist = wishlistService.getById(1);
        Assertions.assertThat(wishlist).isNotNull();
        Assertions.assertThat(wishlist.getProdottiIds()).containsExactlyInAnyOrder(4, 5);
    }

    @Test
    @Order(3)
    public void listAllWishlistsTest() {
        List<WishlistDTO> wishlists = wishlistService.listAll();
        Assertions.assertThat(wishlists).isNotEmpty();
    }

    @Test
    @Order(4)
    public void deleteWishlistTest() throws Exception {
        WishlistRequest req = new WishlistRequest();
        req.setIdWishlist(1);

        wishlistService.delete(req);

        Assertions.assertThatThrownBy(() -> wishlistService.getById(1))
                  .isInstanceOf(Exception.class)
                  .hasMessageContaining("Wishlist non trovata");
    }
}
