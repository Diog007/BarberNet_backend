package com.diogo.barbernet.api.domain.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByCpf(String cpf);

    Optional<Cliente> findByCpf(String cpf);

    Optional<Cliente> findByEmail(String email);
}
