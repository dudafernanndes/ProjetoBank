package br.com.fiap.bank_api.dto;

import br.com.fiap.bank_api.model.Conta;
import br.com.fiap.bank_api.model.TipoConta;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContaResponseDTO(
        Long id,
        String numero,
        String agencia,
        String nomeTitular,
        String cpf,
        LocalDate dataAbertura,
        BigDecimal saldo,
        TipoConta tipo,
        boolean ativa
) {
    public static ContaResponseDTO from(Conta c) {
        return new ContaResponseDTO(
                c.getId(), c.getNumero(), c.getAgencia(), c.getNomeTitular(), c.getCpf(),
                c.getDataAbertura(), c.getSaldo(), c.getTipo(), c.isAtiva()
        );
    }
}
