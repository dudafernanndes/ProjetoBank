package br.com.fiap.bank_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Conta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do titular é obrigatório")
    @Column(nullable = false)
    private String nomeTitular;

    @CPF(message = "CPF inválido")
    @NotBlank(message = "CPF do titular é obrigatório")
    @Column(nullable = false, length = 11)
    private String cpf;

    @PastOrPresent(message = "Data de abertura não pode ser no futuro")
    @Column(nullable = false)
    private LocalDate dataAbertura;

    @DecimalMin(value = "0.00", message = "Saldo não pode ser negativo")
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal saldo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 12)
    private TipoConta tipo;

    @Column(nullable = false)
    private boolean ativa;
}
