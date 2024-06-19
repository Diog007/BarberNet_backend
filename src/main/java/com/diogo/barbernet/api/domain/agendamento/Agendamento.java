package com.diogo.barbernet.api.domain.agendamento;

import com.diogo.barbernet.api.domain.cabeleireiro.Cabeleireiro;
import com.diogo.barbernet.api.domain.cliente.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cabeleireiro_id")
    private Cabeleireiro cabeleireiro;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();

    private LocalDateTime dataHora;

    private BigDecimal precoEstimado;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento status;

    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;

    private String observacao;

    public Agendamento(DadosAgendamentoCorte dados, Cliente cliente, Cabeleireiro cabeleireiro) {
        this.cliente = cliente;
        this.cabeleireiro = cabeleireiro;
        this.dataHora = dados.data();
        this.precoEstimado = dados.precoEstimado();
        this.status = dados.statusAgendamento();
        this.metodoPagamento = dados.metodoPagamento();
        this.observacao = dados.observacao();
    }

    public void atualizar(DadosAtualizarCorte dados, Cliente cliente, Cabeleireiro cabeleireiro) {
        this.cliente = cliente;
        this.cabeleireiro = cabeleireiro;
        this.dataHora = dados.data();
        this.precoEstimado = dados.precoEstimado();
        this.status = dados.statusAgendamento();
        this.metodoPagamento = dados.metodoPagamento();
        this.observacao = dados.observacao();
    }
}