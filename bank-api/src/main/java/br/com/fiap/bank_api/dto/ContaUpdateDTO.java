package br.com.fiap.bank_api.dto;

import jakarta.validation.constraints.*;
import br.com.fiap.bank_api.model.TipoConta;

public record ContaUpdateDTO(
        @NotBlank(message = "{account.holder.notblank}")
        String nomeTitular,

        @NotNull(message = "{account.type.invalid}")
        TipoConta tipo
) {}
