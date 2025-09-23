package br.com.fiap.bank_api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record OperacaoValorDTO(
        @NotNull Long idConta,
        @NotNull @DecimalMin(value = "0.01", message = "Valor deve ser positivo")
        BigDecimal valor
) {}
