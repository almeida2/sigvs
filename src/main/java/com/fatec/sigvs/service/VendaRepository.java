package com.fatec.sigvs.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatec.sigvs.model.Venda;

import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
    List<Venda> findByCpf(String cpf);
}
