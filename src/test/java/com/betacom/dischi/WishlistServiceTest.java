package com.betacom.dischi;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betacom.dischi.services.interfaces.WishlistService;
import com.betacom.dischi.exception.CustomException;
import com.betacom.dischi.models.Prodotto;
import com.betacom.dischi.request.WishlistRequest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class WishlistServiceTest {

    @Autowired
    WishlistService wishlistService;

    @Autowired
    Logger log;

    private static final Integer ID_CLIENTE = 1;
    private static final Integer ID_PRODOTTO = 101;

    @Test
    @Order(1)
    @DisplayName("Test Creazione Wishlist")
    public void testCreateWishlist() {
        WishlistRequest request = new WishlistRequest();
        request.setIdCliente(ID_CLIENTE);
        
        Assertions.assertThatCode(() -> wishlistService.create(request))
                .doesNotThrowAnyException();
        
        log.info("Wishlist creata con successo per cliente ID: {}", ID_CLIENTE);
    }

    @Test
    @Order(2)
    @DisplayName("Test Aggiunta Prodotto a Wishlist")
    public void testAddProductToWishlist() {
        Assertions.assertThatCode(() -> wishlistService.addProductToWishlist(ID_CLIENTE, ID_PRODOTTO))
                .doesNotThrowAnyException();

        log.info("Prodotto ID {} aggiunto con successo alla Wishlist del cliente ID {}", ID_PRODOTTO, ID_CLIENTE);
    }

    @Test
    @Order(3)
    @DisplayName("Test Recupero Prodotti dalla Wishlist")
    public void testGetWishlistProducts() throws CustomException {
        Assertions.assertThatCode(() -> wishlistService.getWishlistProducts(ID_CLIENTE))
                  .doesNotThrowAnyException();

   
        List<Prodotto> prodotti = wishlistService.getWishlistProducts(ID_CLIENTE);

        Assertions.assertThat(prodotti)
                  .isNotNull()
                  .isNotEmpty(); 

        log.info("Prodotti della Wishlist per cliente ID {} recuperati con successo", ID_CLIENTE);
    }


    @Test
    @Order(4)
    @DisplayName("Test Rimozione Prodotto dalla Wishlist")
    public void testRemoveProductFromWishlist() {
        Assertions.assertThatCode(() -> wishlistService.removeProductFromWishlist(ID_CLIENTE, ID_PRODOTTO))
                .doesNotThrowAnyException();

        log.info("Prodotto ID {} rimosso con successo dalla Wishlist del cliente ID {}", ID_PRODOTTO, ID_CLIENTE);
    }

    @Test
    @Order(5)
    @DisplayName("Test Svuotamento Wishlist")
    public void testClearWishlist() {
        Assertions.assertThatCode(() -> wishlistService.clearWishlist(ID_CLIENTE))
                .doesNotThrowAnyException();

        log.info("Wishlist del cliente ID {} svuotata con successo", ID_CLIENTE);
    }
}
