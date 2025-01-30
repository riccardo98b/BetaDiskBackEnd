package com.betacom.dischi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.dischi.models.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente,Integer> {

}
