package com.diogo.barbernet.api.domain.cabeleireiro;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CabeleireiroRepository extends JpaRepository<Cabeleireiro, Long> {
    boolean existsByCpf(String cpf);

    Optional<Cabeleireiro> findByCpf(String cpf);

    Optional<Cabeleireiro> findByEmail(String email);
}
