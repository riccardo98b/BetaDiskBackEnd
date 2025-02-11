package com.betacom.dischi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.betacom.dischi.models.Utente;

@Repository
public interface IUtenteRepository extends JpaRepository<Utente, Integer> {

	Optional<Utente> findByUsername(String username);

	Optional<Utente> findByUsernameAndPassword(String username, String password);

	@Query(name = "utenti.filteredUsers")
	List<Utente> filteredUsers(
			@Param("idUtente") Integer idUtente,
			@Param("username") String username,
			@Param("email") String email
	);

}
