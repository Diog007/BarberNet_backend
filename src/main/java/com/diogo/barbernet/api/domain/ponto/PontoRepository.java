package com.diogo.barbernet.api.domain.ponto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface PontoRepository extends JpaRepository<Ponto, Long> {
    Optional<Ponto> findByCabeleireiroCpf(String cpf);
}
