package com.betacom.dischi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.dischi.models.Recensione;

public interface IRecensioneRepository extends JpaRepository<Recensione, Integer> {

}