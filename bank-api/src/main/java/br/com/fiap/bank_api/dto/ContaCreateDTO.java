package br.com.fiap.bank_api.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import br.com.fiap.bank_api.model.TipoConta;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContaCreateDTO(
        @NotBlank(message = "Nome do titular é obrigatório")
        String nomeTitular,

        @NotBlank @CPF(message = "CPF inválido")
        String cpf,

        @PastOrPresent(message = "Data de abertura não pode ser no futuro")
        LocalDate dataAbertura,

        @DecimalMin(value = "0.00", message = "Saldo inicial não pode ser negativo")
        BigDecimal saldoInicial,

        @NotNull(message = "Tipo da conta é obrigatório")
        TipoConta tipo
) {}
