package com.betacom.dischi;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.betacom.dischi.models.Prodotto;
import com.betacom.dischi.models.Cliente;
import com.betacom.dischi.models.Wishlist;
import com.betacom.dischi.repository.IClienteRepository;
import com.betacom.dischi.repository.IProdottoRepository;
import com.betacom.dischi.repository.IWishlistRepository;
import com.betacom.dischi.request.WishlistRequest;
import com.betacom.dischi.services.interfaces.WishlistService;
import com.betacom.dischi.utilities.Formato;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Transactional
public class WishlistServiceTest {

	@Autowired
	private EntityManager entityManager;

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IProdottoRepository prodottoRepository;

    @Autowired
    private IWishlistRepository wishlistRepository;

    private Cliente testCliente;
    private Prodotto testProdotto;

    @BeforeEach
    public void setUp() {
        // Create a test client
        testCliente = new Cliente();
        testCliente.setNome("Mario");
        testCliente.setCognome("Rossi");
        testCliente.setTelefono("123456789");
        clienteRepository.save(testCliente);

        // Create a test product
        testProdotto = new Prodotto();
        testProdotto.setTitolo("Album Test");
        testProdotto.setArtista("Artista Test");
        testProdotto.setGenere("Rock");
        testProdotto.setDescrizione("Descrizione di test.");
        testProdotto.setAnnoPubblicazione(2025);
        testProdotto.setPrezzo(29.99);
        testProdotto.setFormato(Formato.CD);  // Set the 'formato' value (CD, VINILE, etc.)
        
        prodottoRepository.save(testProdotto);
    }

    @Test
    @Order(1)
    public void testCreateWishlist() throws Exception {
        // Create WishlistRequest
        WishlistRequest req = new WishlistRequest();
        req.setIdCliente(testCliente.getIdCliente());

        // Call the service to create the wishlist
        wishlistService.create(req);

        // Fetch the wishlist and verify it's created
        Optional<Wishlist> wishlist = wishlistRepository.findByCliente(testCliente);
        Assertions.assertThat(wishlist).isPresent();
        Assertions.assertThat(wishlist.get().getProdotti()).isEmpty();
    }

    @Test
    @Order(2)
    @Rollback(false)
    public void testAddProductToWishlist() throws Exception {
        // Create WishlistRequest
        WishlistRequest req = new WishlistRequest();
        req.setIdCliente(testCliente.getIdCliente());
        
        // Create a wishlist first
        wishlistService.create(req);

        // Add product to wishlist
        wishlistService.addProductToWishlist(testCliente.getIdCliente(), testProdotto.getIdProdotto());
        
        // Flush changes to the database
        entityManager.flush();  // Ensure the changes are persisted
        
        // Fetch the wishlist and verify product is added by comparing IDs
        Optional<Wishlist> wishlist = wishlistRepository.findByCliente(testCliente);
        Assertions.assertThat(wishlist).isPresent();
        
        // Compare the ID of the added product with the ones in the wishlist
        Assertions.assertThat(wishlist.get().getProdotti())
            .extracting(Prodotto::getIdProdotto)
            .contains(testProdotto.getIdProdotto());
    }


    @Test
    @Order(3)
    public void testRemoveProductFromWishlist() throws Exception {
        // Verifica che la wishlist esista per il cliente prima di aggiungere il prodotto
        Optional<Wishlist> wishlistOptional = wishlistRepository.findByCliente(testCliente);
        if (!wishlistOptional.isPresent()) {
            // Se la wishlist non esiste, creala prima
            WishlistRequest req = new WishlistRequest();
            req.setIdCliente(testCliente.getIdCliente());
            wishlistService.create(req);
        }

        // Ora aggiungi il prodotto alla wishlist
        wishlistService.addProductToWishlist(testCliente.getIdCliente(), testProdotto.getIdProdotto());

        // Verifica che il prodotto sia stato aggiunto
        wishlistOptional = wishlistRepository.findByCliente(testCliente);
        Assertions.assertThat(wishlistOptional).isPresent();
        Wishlist wishlist = wishlistOptional.get();
        Assertions.assertThat(wishlist.getProdotti()).contains(testProdotto);

        // Ora rimuovi il prodotto dalla wishlist
        wishlistService.removeProductFromWishlist(testCliente.getIdCliente(), testProdotto.getIdProdotto());

        // Verifica che il prodotto sia stato rimosso
        Optional<Wishlist> updatedWishlist = wishlistRepository.findByCliente(testCliente);
        Assertions.assertThat(updatedWishlist).isPresent();
        Assertions.assertThat(updatedWishlist.get().getProdotti()).doesNotContain(testProdotto);
    }



    @Test
    @Order(4)
    public void testClearWishlist() throws Exception {
        // Verifica se la wishlist esiste per il cliente, se non esiste, la crea
        Optional<Wishlist> wishlistOptional = wishlistRepository.findByCliente(testCliente);
        if (!wishlistOptional.isPresent()) {
            WishlistRequest req = new WishlistRequest();
            req.setIdCliente(testCliente.getIdCliente());
            wishlistService.create(req);  // Crea la wishlist se non esiste
        }

        // Aggiungi il prodotto alla wishlist prima di svuotarla
        wishlistService.addProductToWishlist(testCliente.getIdCliente(), testProdotto.getIdProdotto());

        // Ora svuota la wishlist
        wishlistService.clearWishlist(testCliente.getIdCliente());

        // Fetch the wishlist again and verify it's empty
        Optional<Wishlist> wishlist = wishlistRepository.findByCliente(testCliente);
        Assertions.assertThat(wishlist).isPresent();
        Assertions.assertThat(wishlist.get().getProdotti()).isEmpty();  // Verifica che la wishlist sia vuota
    }


    @Test
    @Order(5)
    public void testGetWishlistProducts() throws Exception {
        // Verifica che la wishlist esista per il cliente, se non esiste, la crea
        Optional<Wishlist> wishlistOptional = wishlistRepository.findByCliente(testCliente);
        if (!wishlistOptional.isPresent()) {
            WishlistRequest req = new WishlistRequest();
            req.setIdCliente(testCliente.getIdCliente());
            wishlistService.create(req);  // Crea la wishlist se non esiste
        }

        // Aggiungi il prodotto alla wishlist
        wishlistService.addProductToWishlist(testCliente.getIdCliente(), testProdotto.getIdProdotto());

        // Ottieni i prodotti dalla wishlist
        List<Prodotto> prodotti = wishlistService.getWishlistProducts(testCliente.getIdCliente());

        // Verifica che il prodotto sia nella lista
        Assertions.assertThat(prodotti).contains(testProdotto);
    }


    @Test
    @Order(6)
    public void testSearchWishlistById() throws Exception {
        // Crea la Wishlist se non esiste già
        WishlistRequest req = new WishlistRequest();
        req.setIdCliente(testCliente.getIdCliente());

        Optional<Wishlist> existingWishlist = wishlistRepository.findByCliente(testCliente);
        if (!existingWishlist.isPresent()) {
            System.out.println("Wishlist non trovata, la sto creando per il cliente con ID: " + testCliente.getIdCliente());
            wishlistService.create(req);  // Crea la wishlist per il cliente
            entityManager.flush();  // Forza la persistenza dei cambiamenti nel DB
        } else {
            System.out.println("Wishlist già esistente per il cliente con ID: " + testCliente.getIdCliente());
        }

        // Verifica che la wishlist sia stata creata
        Optional<Wishlist> createdWishlist = wishlistRepository.findByCliente(testCliente);
        Assertions.assertThat(createdWishlist).isPresent();
        System.out.println("Wishlist creata con ID: " + createdWishlist.get().getIdWishlist());

        // Ora cerca la wishlist tramite l'ID del cliente (non l'ID della wishlist)
        Optional<Wishlist> wishlist = wishlistService.searchWishlistById(testCliente.getIdCliente());
        System.out.println("Wishlist trovata per il cliente con ID: " + testCliente.getIdCliente());

        // Verifica che la wishlist sia trovata
        Assertions.assertThat(wishlist).isPresent();
    }


}
