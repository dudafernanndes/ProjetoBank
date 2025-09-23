package br.com.fiap.bank_api.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record PixDTO(
        @NotNull Long idOrigem,
        @NotNull Long idDestino,
        @NotNull @DecimalMin(value = "0.01", message = "Valor deve ser positivo")
        BigDecimal valor
) {}
