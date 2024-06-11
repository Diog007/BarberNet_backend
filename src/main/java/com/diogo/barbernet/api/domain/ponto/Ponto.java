package com.diogo.barbernet.api.domain.ponto;

import com.diogo.barbernet.api.domain.cabeleireiro.Cabeleireiro;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Ponto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cabeleireiro_id")
    private Cabeleireiro cabeleireiro;
    private LocalDateTime entrada;
    private LocalDateTime saida;
}
