package com.betacom.dischi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.dischi.models.Ordine;

public interface IOrdineRepository extends JpaRepository<Ordine, Integer> {


}
