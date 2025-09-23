package br.com.fiap.bank_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import br.com.fiap.bank_api.validation.Agency;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Conta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{account.number.notblank}")
    @Pattern(regexp = "^[0-9]{6,10}$", message = "{account.number.pattern}")
    @Column(nullable = false, length = 10)
    private String numero;

    @NotBlank(message = "{account.agency.notblank}")
    @Agency
    @Column(nullable = false, length = 6)
    private String agencia;

    @NotBlank(message = "{account.holder.notblank}")
    @Column(nullable = false)
    private String nomeTitular;

    @NotBlank(message = "{account.cpf.notblank}")
    @CPF(message = "{account.cpf.invalid}")
    @Column(nullable = false, length = 11)
    private String cpf;

    @PastOrPresent(message = "{account.opening.pastorpresent}")
    @Column(nullable = false)
    private LocalDate dataAbertura;

    @DecimalMin(value = "0.00", message = "{account.balance.positive}")
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal saldo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 12)
    private TipoConta tipo;

    @Column(nullable = false)
    private boolean ativa;
}
