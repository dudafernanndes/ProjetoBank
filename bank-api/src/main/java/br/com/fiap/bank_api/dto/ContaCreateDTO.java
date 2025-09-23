package br.com.fiap.bank_api.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import br.com.fiap.bank_api.model.TipoConta;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContaCreateDTO(
        @NotBlank(message = "{account.number.notblank}")
        @Pattern(regexp = "^[0-9]{6,10}$", message = "{account.number.pattern}")
        String numero,

        @NotBlank(message = "{account.agency.notblank}")
        String agencia,

        @NotBlank(message = "{account.holder.notblank}")
        String nomeTitular,

        @NotBlank(message = "{account.cpf.notblank}") @CPF(message = "{account.cpf.invalid}")
        String cpf,

        @PastOrPresent(message = "{account.opening.pastorpresent}")
        LocalDate dataAbertura,

        @DecimalMin(value = "0.00", message = "{account.balance.positive}")
        BigDecimal saldoInicial,

        @NotNull(message = "{account.type.invalid}")
        TipoConta tipo
) {}
