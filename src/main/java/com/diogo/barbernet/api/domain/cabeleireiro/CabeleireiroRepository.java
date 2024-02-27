package com.diogo.barbernet.api.domain.cabeleireiro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CabeleireiroRepository extends JpaRepository<Cabeleireiro, Long> {
    boolean existsByCpf(String cpf);
}
