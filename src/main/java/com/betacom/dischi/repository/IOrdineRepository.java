package com.betacom.dischi.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.dischi.models.Ordine;

public interface IOrdineRepository extends JpaRepository<Ordine, Integer> {

	List<Ordine> findByDataOrdineAfter(LocalDate dataCreazione);

}
