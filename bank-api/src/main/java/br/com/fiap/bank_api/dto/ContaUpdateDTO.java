package br.com.fiap.bank_api.dto;

import jakarta.validation.constraints.*;
import br.com.fiap.bank_api.model.TipoConta;

public record ContaUpdateDTO(
        @NotBlank(message = "Nome do titular é obrigatório")
        String nomeTitular,

        @NotNull(message = "Tipo da conta é obrigatório")
        TipoConta tipo
) {}
