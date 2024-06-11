package com.diogo.barbernet.api.domain.ponto;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Ponto {

    private Long id;
    private Long cabeleireiroId;
    private LocalDateTime entrada;
    private LocalDateTime saida;
}
