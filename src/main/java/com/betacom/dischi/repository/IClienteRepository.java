package com.betacom.dischi.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.dischi.models.Cliente;


public interface IClienteRepository extends JpaRepository<Cliente,Integer> {

//	@Query(name="clienti.filteredClients")
//	List<Cliente> filteredClients(
//			@Param("idCliente") Integer idCliente,
//			@Param("nome") String nome,
//			@Param("cognome") String cognome,
//            @Param("dataRegistrazione") LocalDate dataRegistrazione
//            // indirizzo,cap,provincia
//			
//			);
	
	@Query(name="clienti.filteredClients")
	List<Cliente> filteredClients(
			@Param("idCliente") Integer idCliente,
			@Param("nome") String nome,
			@Param("cognome") String cognome
			// AND (:dataRegistrazione IS NULL OR FUNCTION('DATE_FORMAT', c.dataRegistrazione, '%Y-%m-%d') LIKE CONCAT(:dataRegistrazione, '%'))

            // indirizzo,cap,provincia
			
			
			);

}
