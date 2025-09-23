package br.com.fiap.bank_api.repository;

import br.com.fiap.bank_api.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> { }
