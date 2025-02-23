package com.betacom.dischi.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.dischi.models.Cliente;


public interface IClienteRepository extends JpaRepository<Cliente,Integer> {


	
	@Query(name="clienti.filteredClients")
	List<Cliente> filteredClients(
			@Param("idCliente") Integer idCliente,
			@Param("nome") String nome,
			@Param("cognome") String cognome,
			@Param("cap") String cap,
			@Param("comune") String comune,
			@Param("provincia") String provincia

			
			);

}
